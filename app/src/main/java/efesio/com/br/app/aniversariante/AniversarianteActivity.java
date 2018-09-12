package efesio.com.br.app.aniversariante;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.AniversarianteBusiness;
import efesio.com.br.app.entities.Aniversariante;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.RuntimeValues;

public class AniversarianteActivity extends ActivityBase
        implements Request.OnResult<List<Aniversariante>>, Request.OnError, Request.OnStart, Request.OnFinish{

    private RecyclerView mRecyclerView;
    AniverAdapter adapter;
    String mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniversariantes);

        Toolbar toolbar =  findViewById(R.id.toolbarAn);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerView_an);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);

        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        mes = dateFormat.format(date);

        aniversariantes();
    }

    private void aniversariantes(){
        new AniversarianteBusiness(this)
                .aniversariantes(mes, RuntimeValues.getIdEmpresa())
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();

    }

    @Override
    public void onStart(String tag) {
        loading(true);
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        alert("Erro ao procurar aniversariantes, tente novamente mais tarde.");
    }

    @Override
    public void onResult(String tag, NixResponse<List<Aniversariante>> res) {
        if (res.getEntity() == null || res.getEntity().size() == 0){
            alert("Nenhum aniversariante este mês");
        }
        if (res.getStatus() != 201){
            alert(res.getMessage());
        }

        adapter = new AniverAdapter(res.getEntity(),this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onFinish(String tag) {
        loading(false);
    }


}
