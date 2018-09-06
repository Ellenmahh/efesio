package efesio.com.br.app.cadastro;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import efesio.com.br.app.R;
import efesio.com.br.app.base.FragmentBase;
import efesio.com.br.app.business.MembroLoginBusiness;
import efesio.com.br.app.entities.IgrejaMembro;
import efesio.com.br.app.entities.Membro;
import efesio.com.br.app.entities.MembroLogin;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.RuntimeValues;
import efesio.com.br.app.util.Util;

public class FragmentCadastro extends FragmentBase
        implements Request.OnResult<MembroLogin>, Request.OnError, Request.OnStart, Request.OnFinish{


    public interface OnCadastro {
        void onCadastro(MembroLogin itens);
    }

    private OnCadastro onCadastro;
    private IgrejaMembro item;
    private EditText criar_email,criar_senha;
    private TextView txt_igreja, txt_nome_user;
    private Button btn_cadastrar;


    public static FragmentCadastro getInstance(IgrejaMembro item, OnCadastro onCadastro){
        FragmentCadastro f = new FragmentCadastro();
        f.onCadastro = onCadastro;
        f.item = item;
        return f;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_cadastro, parent, false);
        criar_email = v.findViewById(R.id.criar_email);
        criar_senha = v.findViewById(R.id.criar_senha);
        txt_igreja = v.findViewById(R.id.txt_igreja);
        txt_nome_user = v.findViewById(R.id.txt_nome_user);
        btn_cadastrar = v.findViewById(R.id.btn_cadastrar);

        if (RuntimeValues.getEmail() != null){
            criar_senha.setText(item.getEmail());
        }else {
            criar_email.setText(item.getEmail());
        }
        txt_igreja.setText(item.getNomeIgreja());
        txt_nome_user.setText("Seja Bem vindo(a),\n "+item.getNome()+"!");

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        return v;
    }

    private void cadastrar(){
        RuntimeValues.setToken("null");
        MembroLogin i = new MembroLogin();
        i.setIdEmpresa(item.getId());
        i.setIdAssinante(item.getIdAssinante());
        i.setEmail(criar_email.getText().toString());
        i.setSenha(Util.toMD5(criar_senha.getText().toString()));
        Membro m = new Membro();
        m.setPrimaryKey(item.getPk());
        i.setMembro(m);
        new MembroLoginBusiness(getContext())
                .cadastrar(i)
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();

    }

    @Override
    public void onStart(String tag) {
        loading(true);
//        Toast.makeText(getContext(),"Cadastrando. . .",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        alert("Erro ao cadastrar usuário, por favor tente mais tarde.");
        System.out.println(e.getMessage());
//        Toast.makeText(getContext(),"Erro: "+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(String tag, NixResponse<MembroLogin> res) {
        if (res.getStatus() != 201){
            alert("Erro ao cadastrar usuário, por favor tente mais tarde.");
            System.out.println(res.getMessage());
//            Toast.makeText(getContext(),"Erro ao cadastrar",Toast.LENGTH_SHORT).show();
            return;
        }
        final MembroLogin m =  res.getEntity();
        alert("Parabéns!", "Você agora pode acessar o app e ficar por dentro das novidades sobre a sua igreja!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentCadastro.this.onCadastro.onCadastro(m);
            }
        });
//        Toast.makeText(getContext(),"Cadastrado com sucesso",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onFinish(String tag) {
        loading(false);
    }
}
