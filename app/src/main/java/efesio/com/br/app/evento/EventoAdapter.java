package efesio.com.br.app.evento;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.Evento;
import efesio.com.br.app.rest.Service;

public class EventoAdapter extends RecyclerView.Adapter {
    private List<Evento> eventos = new ArrayList<>();
    private Context context;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public EventoAdapter(Context context) {
        this.context=context;

        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    public EventoAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.evento_item,
                parent, false);

        return new EventoViewHolder(view);
    }
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder,  int position) {
        System.out.println("onBindViewHolder");
        Evento itemEvento = eventos.get(position);
        EventoViewHolder holder = (EventoViewHolder) viewHolder;

        holder.titulo_evento.setText(itemEvento.getNome());
        if(itemEvento.equals("GRATUITO")){
            holder.tipo_evento.setText(itemEvento.getTipo());
        }
        holder.descricao_evento.setText(itemEvento.getDescricao());
        holder.local_evento.setText(itemEvento.getLocal());
        holder.data_hora.setText(itemEvento.getDataInicio().toString("dd")
                +"/"+itemEvento.getDataInicio().toString("MMM").toUpperCase()
                +" - " + itemEvento.getHoraInicio());
        if (itemEvento.getUrlFoto() != null){
            holder.imagem_evento.setImageUrl(Service.EFESIO.getStorage()+"efesio-bucket-evento-foto/"+itemEvento.getUrlFoto(), mImageLoader);
        }
        if (itemEvento.getUrlFoto() == null || itemEvento.getUrlFoto().isEmpty()){
            holder.imagem_evento.setDefaultImageResId(R.drawable.semfoto);
        }

    }

    @Override
    public int getItemCount() {
        return eventos != null ? eventos.size() : 0;

    }
    public void setItems(List<Evento> items){
        this.eventos.clear();
        this.eventos.addAll(items);
        this.notifyDataSetChanged();
    }

    public Evento getItem(int position) {
        return eventos.get(position);
    }
}
