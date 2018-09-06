package efesio.com.br.app.evento;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import efesio.com.br.app.MainActivity;
import efesio.com.br.app.R;
import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.EventoBusiness;
import efesio.com.br.app.entities.Evento;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.RuntimeValues;
import efesio.com.br.app.widgets.lists.support.ListClickListener;

public class EventoActivity extends ActivityBase
        implements Request.OnResult<List<Evento>>, Request.OnError, Request.OnStart, Request.OnFinish {
    private RecyclerView mRecyclerView;
    private Toolbar toolbarEvento;
    private ListClickListener listener;
    private EventoAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        toolbarEvento = findViewById(R.id.toolbarEvento);
        setSupportActionBar(toolbarEvento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerViewEvento);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        adapter = new EventoAdapter(this);
        mRecyclerView.setAdapter(adapter);

        listener = ListClickListener.addTo(mRecyclerView);
        listener.setOnItemClickListener(new ListClickListener.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Evento e = adapter.getItem(position);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameEvento, EventoFragment.getInstance(e));
                ft.addToBackStack("detalhe");
                ft.commit();
            }
        });
        eventos();
    }

    private void eventos(){
        new EventoBusiness(this)
                .eventos(RuntimeValues.getIdEmpresa())
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();
    }

    @Override
    public void onStart(String tag) {
        loading(true);
//        Toast.makeText(this, "Pesquisando eventos", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        alert("Erro ao procurar eventos, tente novamente mais tarde.");
    }

    @Override
    public void onResult(String tag, final NixResponse<List<Evento>> res) {
        if (res.getEntity() == null || res.getEntity().size() == 0){
            alert("Nenhum evento encontrado");
            open(MainActivity.class);
        }
        if (res.getStatus() != 201){
            alert(res.getMessage());
        }
        if (res.getEntity().size() == 0){
            alert("opssss... ","hão há eventos.");
        }
        adapter.setItems(res.getEntity());
    }

    @Override
    public void onFinish(String tag) {
        loading(false);
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

    public void site(View view) {
//        Toast.makeText(this,"SITE",Toast.LENGTH_SHORT).show();
        String url = "https://efesio.com.br";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void verMais(View view) {
    }
}
