package efesio.com.br.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import efesio.com.br.app.cadastro.list.IgrejaAdapter;

public class IgrejaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    String email;
    String nomeIgreja;
    ArrayList<Integer> conta;
    private IgrejaAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_busca_igreja_lista);

        mRecyclerView = findViewById(R.id.igreja_rv);

        email = getIntent().getStringExtra("email");
        nomeIgreja = getIntent().getStringExtra("nomeIgreja");
        conta = getIntent().getIntegerArrayListExtra("idEmpresa");

        System.out.println("email login "+email);
        System.out.println("nomeIgreja login "+nomeIgreja);
        System.out.println("idEmpresa login "+conta);

        adapter = new IgrejaAdapter(email,nomeIgreja,conta,this);
        mRecyclerView.setAdapter(adapter);
        System.out.println("adapter -- "+adapter);

    }
}
