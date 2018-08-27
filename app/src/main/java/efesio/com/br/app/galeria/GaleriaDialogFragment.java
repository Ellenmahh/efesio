package efesio.com.br.app.galeria;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import efesio.com.br.app.R;

public class GaleriaDialogFragment extends DialogFragment {

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    String imagem;
    NetworkImageView imageView;
    Button btn_fechar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_details, container);
        /**
         * inicia a fila de requisições da voley*/
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imagem = getActivity().getIntent().getStringExtra("image");
        imageView = view.findViewById(R.id.image_details);
        btn_fechar = view.findViewById(R.id.btn_fechar);
        imageView.setImageUrl(imagem, mImageLoader);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btn_fechar.getVisibility() == View.GONE){
                    btn_fechar.setVisibility(View.VISIBLE);
                }else {
                    btn_fechar.setVisibility(View.GONE);
                }
            }
        });
        btn_fechar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDialog().dismiss();
            }
        });



    }

}
