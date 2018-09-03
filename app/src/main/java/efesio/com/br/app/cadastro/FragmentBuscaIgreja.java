package efesio.com.br.app.cadastro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.business.MembroLoginBusiness;
import efesio.com.br.app.entities.IgrejaMembro;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;

public class FragmentBuscaIgreja extends Fragment implements Request.OnResult<List<IgrejaMembro>>, Request.OnError, Request.OnStart, Request.OnFinish{

    public interface BuscaIgrejaCallback{
       void callback(List<IgrejaMembro> itens);
    }

    private EditText cpf_membro;
    private Button buscar;

    private BuscaIgrejaCallback callback;

    public static FragmentBuscaIgreja getInstance(BuscaIgrejaCallback callback){
        FragmentBuscaIgreja f = new FragmentBuscaIgreja();
        f.callback = callback;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_busca_igreja, parent, false);
        cpf_membro = v.findViewById(R.id.cpf_membro);
        buscar = v.findViewById(R.id.buscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarIgreja();
            }
        });

        cpf_membro.setText("123");
        return v;
    }

    private void buscarIgreja(){
        new MembroLoginBusiness(getContext())
                .buscarIgreja(cpf_membro.getText().toString())
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();

    }

    @Override
    public void onStart(String tag) {
        Toast.makeText(getContext(),"Buscando igreja . . .",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        Toast.makeText(getContext(),"Erro: "+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(String tag, NixResponse<List<IgrejaMembro>> res) {
        if (res.getEntity() == null || res.getEntity().size() == 0){
            Toast.makeText(getContext(),"Nenhuma igreja encontrada",Toast.LENGTH_SHORT).show();
            return;
        }
        if (res.getStatus() != 200){
            Toast.makeText(getContext(),"Erro ao buscar",Toast.LENGTH_SHORT).show();
            return;
        }
        List<IgrejaMembro> m =  res.getEntity();
        this.callback.callback(m);
    }

    @Override
    public void onFinish(String tag) {
    }
}
