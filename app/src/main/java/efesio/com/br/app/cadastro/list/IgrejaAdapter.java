package efesio.com.br.app.cadastro.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.IgrejaMembro;

public class IgrejaAdapter extends RecyclerView.Adapter {
    private List<IgrejaMembro> itens = new ArrayList<>();
    private Context context;
    public IgrejaAdapter(Context context, List<IgrejaMembro> itens) {
        this.context = context;
        this.itens = itens;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_busca_igreja_lista_item, parent, false);
        return new IgrejaViewHolder(view);
    }
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder,  int position) {
        System.out.println("onBindViewHolder");
        IgrejaMembro item = getItem(position);
        IgrejaViewHolder holder = (IgrejaViewHolder) viewHolder;

        holder.nome_igreja.setText(item.getNomeIgreja());
        holder.nome_membro.setText(item.getNome());
        holder.cpf_membro.setText(item.getCpf());
        if (item.getFoto() != null){
            holder.img_membro.setImageDrawable(Drawable.createFromPath(item.getFoto()));
        }
    }

    @Override
    public int getItemCount() {
        return itens != null ? itens.size() : 0;
    }

    public void setItems(List<IgrejaMembro> items){
        this.itens.clear();
        this.itens.addAll(items);
        this.notifyDataSetChanged();
    }

    public IgrejaMembro getItem(int position) {
        return itens.get(position);
    }
}
