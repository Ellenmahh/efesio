package efesio.com.br.app.feed;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import efesio.com.br.app.R;
import efesio.com.br.app.agenda.AgendaActivity;
import efesio.com.br.app.aniversariante.AniversarianteActivity;
import efesio.com.br.app.entities.FeedItem;
import efesio.com.br.app.evento.EventoActivity;
import efesio.com.br.app.galeria.ViewPageAdapter;

public class FeedViewHolder extends RecyclerView.ViewHolder {
    Handler mainHandler = new Handler(itemView.getContext().getMainLooper());

    public class MyTimeTask extends TimerTask {

        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(galeria.getCurrentItem()==0){
                        galeria.setCurrentItem(1);
                    }else if (galeria.getCurrentItem()==1){
                        galeria.setCurrentItem(2);

                    }else{
                        galeria.setCurrentItem(0);
                    }
                }
            });
        }
    }

    // compartilhado
    private FeedItem.Tipo tipo;
    private ImageView image ;
    private TextView caption ;

    // agenda
    private TextView subAgenda ;
    private Button visualizar_agenda;

    // galeria
    private ViewPager galeria;
    private LinearLayout sliderDots;
    private int dotscount;
    private ImageView[] dots;

    // evento
    private Button visualizar_evento;
    // aniver
    private Button visualizar_aniver;

    public FeedViewHolder(final View itemview, FeedItem.Tipo tipo) {
        super(itemview);
        this.tipo = tipo;
        construct();
    }

    private void construct(){
        switch (tipo) {
            case GALERIA: {
                galeria = itemView.findViewById(R.id.galeria);
                sliderDots = itemView.findViewById(R.id.sliderDots);
                this.galeria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                this.sliderDots.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                break;
            }
            case AGENDA: {
                this.image = itemView.findViewById(R.id.image);
                this.caption = itemView.findViewById(R.id.headerAgenda);
                this.visualizar_agenda = itemView.findViewById(R.id.visualizar_agenda);
                this.visualizar_agenda.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), AgendaActivity.class);
                        v.getContext().startActivity(i);
                    }
                });
                break;
            }
            case EVENTOS: {
                this.image = itemView.findViewById(R.id.imageEvento);
                this.caption = itemView.findViewById(R.id.captionEvento);
                this.visualizar_evento = itemView.findViewById(R.id.visualizar_evento);
                this.visualizar_evento.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), EventoActivity.class);
                        v.getContext().startActivity(i);
                    }
                });

                break;
            }
            case ANIVERSARIANTE: {
                this.image = itemView.findViewById(R.id.imageAniver);
                this.caption = itemView.findViewById(R.id.captionAniver);
                this.visualizar_aniver = itemView.findViewById(R.id.visualizar_aniver);
                this.visualizar_aniver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), AniversarianteActivity.class);
                        v.getContext().startActivity(i);
                    }
                });
                break;
            }
        }
    }

    public void fill(FeedItem item){
        switch (item.getTipo()) {
            case GALERIA:{
                ViewPageAdapter adapter = new ViewPageAdapter(itemView.getContext());

                galeria.setAdapter(adapter);
                dotscount=adapter.getCount();
                dots = new ImageView[dotscount];

                for(int i=0;i<dotscount;i++){
                    dots[i]=new ImageView(itemView.getContext());
                    dots[i].setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),R.drawable.nonactive_dot));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(8, 0, 8, 0);
                    sliderDots.addView(dots[i], params);
                } if (dots.length > 0) {
                }

                if (dots.length > 0) {

                    dots[0].setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.active_dot));
                    galeria.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            for (int i = 0; i < dotscount; i++) {

                                dots[i].setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.nonactive_dot));
                                dots[position].setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.active_dot));
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                }
                // definindo o tempo de passagem das imagens do slide_promocao
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new MyTimeTask(), 8000, 6000);
                break;

            }
            case AGENDA: {
                caption.setText(item.legenda);
                Drawable myIcon = itemView.getResources().getDrawable(item.photoId);
                image.setImageDrawable(myIcon);

                break;
            }
            case EVENTOS: {
                caption.setText(item.getLegenda());
                Drawable myIcon = itemView.getResources().getDrawable(item.photoId);
                image.setImageDrawable(myIcon);
                break;
            }
            case ANIVERSARIANTE: {
                caption.setText(item.getLegenda());
                Drawable myIcon = itemView.getResources().getDrawable(item.photoId);
                image.setImageDrawable(myIcon);
                break;
            }

        }
    }


}