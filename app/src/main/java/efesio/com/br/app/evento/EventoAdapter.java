package efesio.com.br.app.evento;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.Evento;

public class EventoAdapter extends RecyclerView.Adapter {
    private List<Evento> eventos;
    private Context context;


    public EventoAdapter(List<Evento> lstEventos, Context context) {
        this.eventos = lstEventos;
        this.context = context;
    }

    public EventoAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.evento_item,
                parent, false);

        return new EventoViewHolder(view);
    }
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder,  int position) {

        EventoViewHolder holder = (EventoViewHolder) viewHolder;
        Evento itemEvento = eventos.get(position);

        holder.nome_evento.setText(itemEvento.getNome());
        holder.privacidade.setText(itemEvento.getPrivacidade());
        holder.local_evento.setText(itemEvento.getLocal());
        holder.data_ini.setText(itemEvento.getDataInicio());
        holder.data_fim.setText(itemEvento.getDataTermino());

        if (itemEvento.getUrlBanner() == null || itemEvento.getUrlBanner().trim().isEmpty()) {
            holder.banner_evento.setVisibility(View.GONE);
            Picasso.with(context.getApplicationContext())
                    .load(itemEvento.getUrlFoto())
//                    .resize(300, 280)
                    .into(holder.imagem_evento);
        }if (itemEvento.getUrlFoto() == null || itemEvento.getUrlFoto().trim().isEmpty()){
            holder.imagem_evento.setVisibility(View.GONE);
            Picasso.with(context.getApplicationContext())
                    .load(itemEvento.getUrlBanner())
                    .into(holder.banner_evento);
        }

    }

    @Override
    public int getItemCount() {
        return eventos != null ? eventos.size() : 0;

    }
}
