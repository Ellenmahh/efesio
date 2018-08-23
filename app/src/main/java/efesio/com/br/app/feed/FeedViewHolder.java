package efesio.com.br.app.feed;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import efesio.com.br.app.R;
import efesio.com.br.app.galeria.ViewPageAdapter;
import efesio.com.br.app.agenda.AgendaActivity;
import efesio.com.br.app.entities.FeedItem;
import efesio.com.br.app.evento.EventoActivity;

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

        // galeria
        private ViewPager galeria;
        private LinearLayout sliderDots;
        private int dotscount;
        private ImageView[] dots;

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
                    break;
                }
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
                    timer.scheduleAtFixedRate(new MyTimeTask(), 6000, 6000);
                    break;

                }
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