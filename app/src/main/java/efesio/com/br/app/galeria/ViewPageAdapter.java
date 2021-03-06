package efesio.com.br.app.galeria;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.base.LoadingFragment;
import efesio.com.br.app.business.GaleriaBusiness;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.RuntimeValues;

public class ViewPageAdapter extends PagerAdapter
        implements Request.OnResult<List<String>>, Request.OnError, Request.OnStart, Request.OnFinish {

    public interface GaleriaCallback{
        void onLoad(List<String> imagens);
    }

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> images = new ArrayList<>();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private NetworkImageView imageView;
    private GaleriaCallback callback;
    /**
     * inicia a fila de requisições da voley*/
    public ViewPageAdapter(Context context, GaleriaCallback callback){
        this.context = context;
        this.callback = callback;
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

        new GaleriaBusiness(context)
                .galeria(RuntimeValues.getIdEmpresa())
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();
    }

    @Override
    public int getCount() {
        return images != null ? images.size() : 0;
    }

    public void setItems(List<String> items){
        this.images.clear();
        this.images.addAll(items);
        this.callback.onLoad(items);
        this.notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position){
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout,null);
        imageView= view.findViewById(R.id.img_custom);
        String imagem = images.get(position);
        imageView.setImageUrl(imagem, mImageLoader);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GaleriaActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    @Override
    public void onStart(String tag) {
        loading(true);
//        Toast.makeText(context,"Carregando imagens", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String tag, Exception e) {
        Toast.makeText(context,"Erro ao carregar imagens", Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

    @Override
    public void onResult(String tag, NixResponse<List<String>> res) {
        setItems(res.getEntity());
    }

    @Override
    public void onFinish(String tag) {
        loading(false);
//        Toast.makeText(context,"Imagens Carregadas", Toast.LENGTH_SHORT).show();
    }

    public final void loading(boolean loading){
        FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        if(!loading) {
            DialogFragment prev = (DialogFragment) ((FragmentActivity)context).getSupportFragmentManager().findFragmentByTag("loading_fragment");
            if (prev != null) {
                prev.dismiss();
                ft.remove(prev);
            }
        }else{
            ft.addToBackStack(null);
            LoadingFragment.newInstance().show(ft, "loading_fragment");
        }
    }
}