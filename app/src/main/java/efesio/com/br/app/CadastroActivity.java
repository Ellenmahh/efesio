package efesio.com.br.app;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.cadastro.FragmentBuscaIgreja;
import efesio.com.br.app.cadastro.FragmentBuscaIgrejaLista;
import efesio.com.br.app.cadastro.FragmentCadastro;
import efesio.com.br.app.entities.IgrejaMembro;
import efesio.com.br.app.entities.MembroLogin;

public class CadastroActivity extends ActivityBase implements FragmentBuscaIgreja.BuscaIgrejaCallback,
        FragmentBuscaIgrejaLista.BuscaIgrejaOnSelect, FragmentCadastro.OnCadastro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        addFragment(R.id.view, FragmentBuscaIgreja.getInstance(this), "BUSCA");
    }

    @Override
    public void callback(List<IgrejaMembro> itens) {
        System.out.println("ITENS ENCONTRADOS:\n");
        System.out.println(itens);
        if(itens.size() > 1) {
            addFragment(R.id.view, FragmentBuscaIgrejaLista.getInstance(itens,this), "LISTA");
        }else{
            addFragment(R.id.view, FragmentCadastro.getInstance(itens.get(0),this), "CADASTRO");
        }
        hideFragment("BUSCA");
    }

    @Override
    public void onSelect(IgrejaMembro item) {
        System.out.println("ITEM SELECIONADO:\n");
        System.out.println(item);
        addFragment(R.id.view, FragmentCadastro.getInstance(item,this), "CADASTRO");
        hideFragment("LISTA");
    }

    @Override
    public void onCadastro(MembroLogin item) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("email", item.getEmail());
        intent.putExtra("senha", item.getSenha());
        startActivity(intent);
    }
}

