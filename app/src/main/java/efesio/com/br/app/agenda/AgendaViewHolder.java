package efesio.com.br.app.agenda;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import efesio.com.br.app.R;

public class AgendaViewHolder extends RecyclerView.ViewHolder {

    TextView titulo_ag,descricao_ag;

    public AgendaViewHolder(@NonNull View itemView) {
        super(itemView);
        titulo_ag = itemView.findViewById(R.id.titulo_ag);
        descricao_ag = itemView.findViewById(R.id.descricao_ag);
    }
}
