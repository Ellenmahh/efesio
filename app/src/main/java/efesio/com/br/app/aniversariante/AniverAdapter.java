package efesio.com.br.app.aniversariante;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.Aniversariante;

public class AniverAdapter extends RecyclerView.Adapter {
    private List<Aniversariante> aniver;
    private Context context;


    public AniverAdapter(Context context) {
        this.context = context;
    }

    public AniverAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.aniver_item,
                parent, false);

        return new AniverViewHolder(view);
    }
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder,  int position) {

        AniverViewHolder holder = (AniverViewHolder) viewHolder;
        Aniversariante item = aniver.get(position);

        holder.nome_membro.setText(item.getNome());
        holder.dtNasc_membro.setText(item.getDtNasc());


        if (item.getFoto() == null || item.getFoto().trim().isEmpty()) {
            Picasso.with(context.getApplicationContext())
                    .load(item.getFoto())
                    .resize(300, 280)
                    .into(holder.img_membro);
        }
    }

    @Override
    public int getItemCount() {
        return aniver != null ? aniver.size() : 0;

    }

    public void setItems(List<Aniversariante> items){
        this.aniver.clear();
        this.aniver.addAll(items);
        this.notifyDataSetChanged();
    }
}
