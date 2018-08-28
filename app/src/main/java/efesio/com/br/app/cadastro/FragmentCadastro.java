package efesio.com.br.app.cadastro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import efesio.com.br.app.R;
import efesio.com.br.app.business.MembroBusiness;
import efesio.com.br.app.entities.IgrejaMembro;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;

public class FragmentCadastro extends Fragment
        implements Request.OnResult<IgrejaMembro>, Request.OnError, Request.OnStart, Request.OnFinish{

    public interface OnCadastro {
        void onCadastro(IgrejaMembro itens);
    }

    private EditText cpf_membro;
    private Button cadastrar;
    private OnCadastro onCadastro;

    public static FragmentCadastro getInstance(OnCadastro onCadastro){
        FragmentCadastro f = new FragmentCadastro();
        f.onCadastro = onCadastro;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_cadastro, parent, false);
        cpf_membro = v.findViewById(R.id.cpf_membro);
        cadastrar = v.findViewById(R.id.buscar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        cpf_membro.setText("123");
        return v;
    }

    private void cadastrar(){
        IgrejaMembro i = new IgrejaMembro();

        new MembroBusiness(getContext())
                .cadastrar(i)
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();

    }

    @Override
    public void onStart(String tag) {
        Toast.makeText(getContext(),"Buscando",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        Toast.makeText(getContext(),"Erro: "+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(String tag, NixResponse<IgrejaMembro> res) {
        if (res.getStatus() != 201){
            Toast.makeText(getContext(),"Erro ao cadastrar",Toast.LENGTH_SHORT).show();
            return;
        }
        IgrejaMembro m =  res.getEntity();
        this.onCadastro.onCadastro(m);
    }

    @Override
    public void onFinish(String tag) {
    }
}
