package efesio.com.br.app.aniversariante;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.Aniversariante;
import efesio.com.br.app.rest.Service;

public class AniverAdapter extends RecyclerView.Adapter {
    private List<Aniversariante> aniver;
    private Context context;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public AniverAdapter(List<Aniversariante> lstAniver, Context context) {
        this.aniver = lstAniver;
        this.context = context;

        /**
         * inicia a fila de requisições da voley*/
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

    public AniverAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.aniver_item,
                parent, false);

        return new AniverViewHolder(view);
    }
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder,  int position) {
        System.out.println("onBindViewHolder");

        Aniversariante item = aniver.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date =sdf.format(new Date());
        AniverViewHolder holder = (AniverViewHolder) viewHolder;

        holder.nome_membro.setText(item.getNome());
        holder.dtNasc_membro.setText(date);

        if (item.getUrlFoto() == null || item.getUrlFoto().isEmpty()){
            holder.img_membro.setDefaultImageResId(R.drawable.semfoto);

        }
        /**
         * carrega a imagem da url e mostra na imageview*/
        holder.img_membro.setImageUrl( Service.EFESIO.getStorage()+"efesio-bucket-membro/"+item.getUrlFoto(), mImageLoader);

    }

    @Override
    public int getItemCount() {
        return aniver != null ? aniver.size() : 0;
    }

}
