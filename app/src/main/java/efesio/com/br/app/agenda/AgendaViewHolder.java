package efesio.com.br.app.agenda;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import efesio.com.br.app.R;

public class AgendaViewHolder extends RecyclerView.ViewHolder {

    TextView titulo_ag,descricao_ag, hora_agenda, dia_agenda,mes_agenda;

    public AgendaViewHolder(@NonNull View itemView) {
        super(itemView);
        titulo_ag = itemView.findViewById(R.id.titulo_ag);
        descricao_ag = itemView.findViewById(R.id.descricao_ag);
        hora_agenda = itemView.findViewById(R.id.hora_agenda);
        dia_agenda = itemView.findViewById(R.id.dia_agenda);
        mes_agenda = itemView.findViewById(R.id.mes_agenda);
    }
}
