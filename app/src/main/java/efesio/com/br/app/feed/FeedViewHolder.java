package efesio.com.br.app.feed;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import efesio.com.br.app.agenda.AgendaActivity;
import efesio.com.br.app.R;
import efesio.com.br.app.entities.FeedItem;
import efesio.com.br.app.evento.EventoActivity;

public class FeedViewHolder extends RecyclerView.ViewHolder {

        // compartilhado
        private FeedItem.Tipo tipo;
        private ImageView image ;
        private TextView caption ;
        
        // agenda
        private TextView subAgenda ;
        

        public FeedViewHolder(final View itemview, FeedItem.Tipo tipo) {
            super(itemview);
            this.tipo = tipo;
            construct();
        }

        private void construct(){
            switch (tipo) {
                case AGENDA: {
                    this.image = itemView.findViewById(R.id.image);
                    this.caption = itemView.findViewById(R.id.headerAgenda);
                    this.subAgenda = itemView.findViewById(R.id.subAgenda);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                    break;
                }
                case EVENTOS: {
                    this.image = itemView.findViewById(R.id.imageEvento);
                    this.caption = itemView.findViewById(R.id.captionEvento);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                    break;
                }
                case GALERIA: {
                    break;
                }
            }
        }


        public void fill(FeedItem item){
            switch (item.getTipo()) {
                case AGENDA: {
                    caption.setText(item.legenda);
                    subAgenda.setText("Agenda teste bla bla bla");
                    Drawable myIcon = itemView.getResources().getDrawable(item.photoId);
                    image.setImageDrawable(myIcon);
                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), AgendaActivity.class);
                            v.getContext().startActivity(intent);
                        }
                    });
                    break;
                }
                case EVENTOS: {
                    caption.setText(item.legenda);
                    Drawable myIcon = itemView.getResources().getDrawable(item.photoId);
                    image.setImageDrawable(myIcon);
                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), EventoActivity.class);
                            v.getContext().startActivity(intent);
                        }
                    });
                    break;
                }
            }
        }
    }