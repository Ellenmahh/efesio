package efesio.com.br.app.cadastro.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.IgrejaMembro;

public class IgrejaAdapter extends RecyclerView.Adapter {
    private List<IgrejaMembro> igreja = new ArrayList<>();
    private Context context;
    public IgrejaAdapter(Context context) {
        this.context = context;
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
        IgrejaMembro item = igreja.get(position);
        IgrejaViewHolder holder = (IgrejaViewHolder) viewHolder;

        holder.nome_igreja.setText(item.getNomeIgreja());
    }

    @Override
    public int getItemCount() {
        return igreja != null ? igreja.size() : 0;
    }

    public void setItems(List<IgrejaMembro> items){
        this.igreja.clear();
        this.igreja.addAll(items);
        this.notifyDataSetChanged();
    }
}
