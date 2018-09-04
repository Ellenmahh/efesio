package efesio.com.br.app.aniversariante;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import efesio.com.br.app.R;

public class AniverViewHolder extends RecyclerView.ViewHolder   {

    CircularNetworkImageView img_membro;
    TextView nome_membro,dtNasc_membro;

    public AniverViewHolder(@NonNull View itemView) {
        super(itemView);

        img_membro = itemView.findViewById(R.id.img_membro);
        nome_membro = itemView.findViewById(R.id.nome_membro);
        dtNasc_membro = itemView.findViewById(R.id.dtNasc);
    }
}
