package efesio.com.br.app.galeria;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import efesio.com.br.app.R;

public class DetailsActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getWindow().getAttributes().windowAnimations = R.style.AppTheme_Detail;

        /**
         * inicia a fila de requisições da voley*/
        mRequestQueue = Volley.newRequestQueue(DetailsActivity.this);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });

        String imagem = getIntent().getStringExtra("image");
        NetworkImageView imageView = findViewById(R.id.image_details);
        imageView.setImageUrl(String.valueOf(imagem), mImageLoader);
    }
}
