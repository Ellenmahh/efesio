package efesio.com.br.app.agenda;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.Agenda;

public class AgendaAdapter extends RecyclerView.Adapter  {
    private List<Agenda> agenda = new ArrayList<>();
    private Context context;

    public AgendaAdapter(Context context) {
        this.context = context;
    }

    public AgendaAdapter() {

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.agenda_item,
                parent, false);



        return new AgendaViewHolder(view);
    }
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder,  int position) {
        System.out.println("onBindViewHolder");
        AgendaViewHolder holder = (AgendaViewHolder) viewHolder;
        Agenda itemAgenda = agenda.get(position);
        holder.titulo_ag.setText(itemAgenda.getTitulo());
        holder.descricao_ag.setText(itemAgenda.getDescricao());
        holder.dia_agenda.setText(itemAgenda.getData().toString("dd"));
        holder.mes_agenda.setText(itemAgenda.getData().toString("MMM").toUpperCase());
        holder.hora_agenda.setText(itemAgenda.getHora_inicial() +" - " + itemAgenda.getHora_termino());
    }

    @Override
    public int getItemCount() {
        return agenda != null ? agenda.size() : 0;
    }

    public void setItems(List<Agenda> items){
        this.agenda.clear();
        this.agenda.addAll(items);
        this.notifyDataSetChanged();
    }
}
