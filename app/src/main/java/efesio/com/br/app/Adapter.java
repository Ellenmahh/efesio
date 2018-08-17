package efesio.com.br.app;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Adapter extends RecyclerView.Adapter<PhotoAlbum.PhotoViewHolder> {
    private PhotoAlbum mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter(PhotoAlbum album) {
        mDataset = album;
    }
    @NonNull
    @Override
    public PhotoAlbum.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_agenda_item, parent, false);
        return new PhotoAlbum.PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAlbum.PhotoViewHolder viewHolder, int position) {
        Photo photo = mDataset.getPhoto(position);
        viewHolder.getCaption().setText(photo.legenda);
        Drawable myIcon = viewHolder.itemView.getResources().getDrawable( photo.photoId );
        viewHolder.getImage().setImageDrawable(myIcon);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
