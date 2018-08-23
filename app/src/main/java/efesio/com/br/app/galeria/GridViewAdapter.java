package efesio.com.br.app.galeria;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.ImageItem;

public class GridViewAdapter extends ArrayAdapter
{
	private Context context;
	private int layoutResourceId;
	private ArrayList data ;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	public GridViewAdapter(Context context, int layoutResourceId, List<String> entity) {
		super(context, layoutResourceId);
		this.layoutResourceId = layoutResourceId;
		this.context = context;

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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		GaleriaActivity.ViewHolder holder = null;
		ImageItem item = (ImageItem) data.get(position);
		if (row == null) {
			row = LayoutInflater.from(context).inflate(R.layout.galeria_item, parent, false);
			holder = new GaleriaActivity.ViewHolder();
			holder.imageTitle = row.findViewById(R.id.textg);
			holder.image =  row.findViewById(R.id.imageg);

			data.addAll(Collections.singleton(item.getImage()))
			row.setTag(holder);
		} else {
			holder = (GaleriaActivity.ViewHolder) row.getTag();
		}

		holder.imageTitle.setText(item.getTitle());
		/**
		 * carrega a imagem da url e mostra na imageview*/
		String imagem = String.valueOf(data.get(position));
		holder.image.setImageUrl(imagem, mImageLoader);

		System.out.println("imagem--- " + imagem );
		return row;
	}


}