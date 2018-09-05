package efesio.com.br.app.evento;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import efesio.com.br.app.R;

public class EventoViewHolder extends RecyclerView.ViewHolder   {

    NetworkImageView imagem_evento;
    TextView titulo_evento,local_evento,descricao_evento,data_hora, tipo_evento;
    Button verMais;

    public EventoViewHolder(@NonNull View itemView) {
        super(itemView);
        imagem_evento = itemView.findViewById(R.id.imagem_evento);
        titulo_evento = itemView.findViewById(R.id.titulo_evento);
        tipo_evento = itemView.findViewById(R.id.tipo_evento);
        local_evento = itemView.findViewById(R.id.local_evento);
        descricao_evento = itemView.findViewById(R.id.descricao_evento);
        data_hora = itemView.findViewById(R.id.data_hora);
        verMais = itemView.findViewById(R.id.verMais);




    }
}
