package efesio.com.br.app.cadastro.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import efesio.com.br.app.R;

public class IgrejaViewHolder extends RecyclerView.ViewHolder   {
    TextView nome_igreja, nome_membro, cpf_membro;
    ImageView img_membro;

    public IgrejaViewHolder(@NonNull View itemView) {
        super(itemView);
        nome_igreja = itemView.findViewById(R.id.nome_igreja);
        nome_membro = itemView.findViewById(R.id.nome_membro);
        cpf_membro = itemView.findViewById(R.id.cpf_membro);
        img_membro = itemView.findViewById(R.id.img_membro);
    }
}
