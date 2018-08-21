package efesio.com.br.app.evento;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import efesio.com.br.app.R;

public class EventoViewHolder extends RecyclerView.ViewHolder   {

    ImageView banner_evento, imagem_evento;
    TextView nome_evento,privacidade,local_evento,data_ini,data_fim;

    public EventoViewHolder(@NonNull View itemView) {
        super(itemView);
        banner_evento = itemView.findViewById(R.id.banner_evento);
        imagem_evento = itemView.findViewById(R.id.imagem_evento);
        nome_evento = itemView.findViewById(R.id.nome_evento);
        privacidade = itemView.findViewById(R.id.privacidade);
        local_evento = itemView.findViewById(R.id.local_evento);
        data_ini = itemView.findViewById(R.id.data_ini);
        data_fim = itemView.findViewById(R.id.data_fim);

    }
}
