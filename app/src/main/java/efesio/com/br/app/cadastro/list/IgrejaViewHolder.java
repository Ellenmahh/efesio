package efesio.com.br.app.cadastro.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import efesio.com.br.app.R;

public class IgrejaViewHolder extends RecyclerView.ViewHolder   {
    TextView nome_igreja;

    public IgrejaViewHolder(@NonNull View itemView) {
        super(itemView);
        nome_igreja = itemView.findViewById(R.id.nome_igreja);

    }
}
