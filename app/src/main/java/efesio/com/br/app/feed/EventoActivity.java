package efesio.com.br.app.feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.business.EventoBusiness;
import efesio.com.br.app.entities.Evento;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;

public class EventoActivity extends AppCompatActivity
        implements Request.OnResult<List<Evento>>, Request.OnError, Request.OnStart, Request.OnFinish {
    ImageView banner_evento, imagem_evento;
    TextView nome_evento,privacidade,local_evento,data_ini,data_fim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        banner_evento = findViewById(R.id.banner_evento);
        imagem_evento = findViewById(R.id.imagem_evento);
        nome_evento = findViewById(R.id.nome_evento);
        privacidade = findViewById(R.id.privacidade);
        local_evento = findViewById(R.id.local_evento);
        data_ini = findViewById(R.id.data_ini);
        data_fim = findViewById(R.id.data_fim);

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
        Toast.makeText(this, "Pesquisando eventos", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResult(String tag, NixResponse<List<Evento>> res) {
        System.out.println("HEADERS ============= "+res.getHeaders().get("nome"));
        nome_evento.setText(res.getHeaders().get("nome"));
    }

    @Override
    public void onFinish(String tag) {

    }
}
