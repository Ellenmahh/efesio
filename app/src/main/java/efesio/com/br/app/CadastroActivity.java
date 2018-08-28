package efesio.com.br.app;

import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.List;

import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.cadastro.FragmentBuscaIgreja;
import efesio.com.br.app.cadastro.FragmentBuscaIgrejaLista;
import efesio.com.br.app.cadastro.FragmentCadastro;
import efesio.com.br.app.entities.IgrejaMembro;

public class CadastroActivity extends ActivityBase implements FragmentBuscaIgreja.BuscaIgrejaCallback,
        FragmentBuscaIgrejaLista.BuscaIgrejaOnSelect, FragmentCadastro.OnCadastro {

    private FrameLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.view = findViewById(R.id.view);


    }

    @Override
    public void callback(List<IgrejaMembro> itens) {

    }

    @Override
    public void onSelect(IgrejaMembro item) {

    }

    @Override
    public void onCadastro(IgrejaMembro itens) {

    }
}

