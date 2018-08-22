package efesio.com.br.app.aniversariante;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import efesio.com.br.app.MainActivity;
import efesio.com.br.app.R;
import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.AniversarianteBusiness;
import efesio.com.br.app.entities.Aniversariante;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;

public class AniversariantesActivity extends ActivityBase
        implements Request.OnResult<List<Aniversariante>>, Request.OnError, Request.OnStart, Request.OnFinish{

    private RecyclerView mRecyclerView;
    private AniverAdapter adapter = new AniverAdapter(this );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniversariantes);
        Toolbar toolbar =  findViewById(R.id.toolbarAg);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerView_an);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);

        aniversariantes();
    }

    private void aniversariantes(){
        new AniversarianteBusiness(this)
                .aniversariantes()
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();

    }

    @Override
    public void onStart(String tag) {
        loading(true);
        //Toast.makeText(this, "Pesquisando eventos", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        alert("Erro ao procurar eventos, tente novamente mais tarde.");
//        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResult(String tag, NixResponse<List<Aniversariante>> res) {
        if (res.getEntity() == null || res.getEntity().size() == 0){
            open(MainActivity.class);
            alert("Nenhum evento encontrado");
        }
        if (res.getStatus() != 200){
            alert(res.getMessage());
//            Toast.makeText(this,  res.getMessage(), Toast.LENGTH_LONG).show();
        }
        adapter.setItems(res.getEntity());

        System.out.println("getMessage ============= "+res.getMessage());
        System.out.println("HEADERS ============= "+res.getHeaders());
        System.out.println("ENTITY ============= "+res.getEntity());

    }

    @Override
    public void onFinish(String tag) {
        loading(false);
    }


}
