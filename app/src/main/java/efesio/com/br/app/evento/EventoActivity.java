package efesio.com.br.app.evento;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import efesio.com.br.app.MainActivity;
import efesio.com.br.app.R;
import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.EventoBusiness;
import efesio.com.br.app.entities.Evento;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;

public class EventoActivity extends ActivityBase
        implements Request.OnResult<List<Evento>>, Request.OnError, Request.OnStart, Request.OnFinish {

    private RecyclerView mRecyclerView;
    private EventoAdapter adapter = new EventoAdapter(this );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        mRecyclerView = findViewById(R.id.recyclerViewEvento);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);

        eventos();
    }

    private void eventos(){
        new EventoBusiness(this)
                .eventos()
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
    public void onResult(String tag, NixResponse<List<Evento>> res) {
        if (res.getEntity() == null || res.getEntity().size() == 0){
            open(MainActivity.class);
            alert("Nenhum evento encontrado");
        }
        if (res.getStatus() != 200){
            alert(res.getMessage());
        }
        adapter.setItems(res.getEntity());


    }

    @Override
    public void onFinish(String tag) {
        loading(false);
    }


}
