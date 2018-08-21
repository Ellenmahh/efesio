package efesio.com.br.app.agenda;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.Agenda;

public class AgendaAdapter extends RecyclerView.Adapter  {
    private List<Agenda> agenda;
    private Context context;

    public AgendaAdapter(List<Agenda> lstAgenda, Context context) {
        this.agenda = lstAgenda;
        this.context = context;
    }

    public AgendaAdapter() {

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.agenda_item,
                parent, false);

        return new AgendaViewHolder(view);
    }
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder,  int position) {

        AgendaViewHolder holder = (AgendaViewHolder) viewHolder;
        Agenda itemAgenda = agenda.get(position);
        holder.titulo_ag.setText(itemAgenda.getTitulo());
        holder.descricao_ag.setText(itemAgenda.getDescricao());

    }

    @Override
    public int getItemCount() {
        return agenda != null ? agenda.size() : 0;

    }
}
