package efesio.com.br.app.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import java.util.List;

import efesio.com.br.app.LoginActivity;
import efesio.com.br.app.R;
import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.entities.IgrejaMembro;
import efesio.com.br.app.entities.MembroLogin;
import efesio.com.br.app.util.RuntimeValues;

public class CadastroActivity extends ActivityBase implements FragmentBuscaIgreja.BuscaIgrejaCallback,
        FragmentBuscaIgrejaLista.BuscaIgrejaOnSelect, FragmentCadastro.OnCadastro {
    private Toolbar toolbarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        toolbarca = findViewById(R.id.toolbarca);
        setSupportActionBar(toolbarca);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void callback(List<IgrejaMembro> itens) {
        System.out.println("ITENS ENCONTRADOS:\n");
        System.out.println(itens);
        getSupportFragmentManager().popBackStack();
        if(itens.size() > 1) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.view, FragmentBuscaIgrejaLista.getInstance(itens,this));
            ft.addToBackStack("LISTA");
            ft.commit();
        }else{
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.view, FragmentCadastro.getInstance(itens.get(0),this));
            ft.addToBackStack("CADASTRO");
            ft.commit();
        }
    }

    @Override
    public void onSelect(IgrejaMembro item) {
        System.out.println("ITEM SELECIONADO:\n");
        System.out.println(item);
        getSupportFragmentManager().popBackStack();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.view, FragmentCadastro.getInstance(item,this));
        ft.addToBackStack("CADASTRO");
        ft.commit();
    }

    @Override
    public void onCadastro(MembroLogin item) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("email", item.getEmail());
        startActivity(intent);
        RuntimeValues.setEmail(item.getEmail());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            // remove a ultima transação
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}

