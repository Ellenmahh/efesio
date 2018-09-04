package efesio.com.br.app.galeria;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import efesio.com.br.app.R;

public class GridViewAdapter extends ArrayAdapter {
	private Context context;
	private int layoutResourceId;
	private ArrayList<String> image;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;


	public GridViewAdapter(Context context, int layoutResourceId, ArrayList<String> image) {
		super(context, layoutResourceId, image);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.image = image;

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

	@Override
	public int getCount() {
		return image != null ? image.size() : 0;
	}

	/**Atualiza a lista de fotos */

	public void setItems(List<String> items){
		this.image.clear();
		this.image.addAll(items);
		this.notifyDataSetChanged();
	}


	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder ;
		NetworkImageView imageView = null;

		row = LayoutInflater.from(context).inflate(R.layout.galeria_item, parent, false);
		holder = new ViewHolder();
		imageView= row.findViewById(R.id.image_galeria);
		holder.image =  imageView;
		row.setTag(holder);

		String imagem = image.get(position);
		imageView.setImageUrl(imagem, mImageLoader);

		return row;

	}

	static class ViewHolder {
		NetworkImageView image;
	}
}