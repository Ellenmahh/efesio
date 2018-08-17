package efesio.com.br.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PhotoAlbum {
    private final static Photo[] listPhoto ={new Photo( R.drawable.thor, "Ahsan 1"), new Photo( R.drawable.thanos,  "Ahsan 2")};
    private final Photo[] photos;
    private final Random random;

    public PhotoAlbum(){
        this.photos = listPhoto;
        random = new Random();
    }

    public int size(){
        return this.photos.length;
    }

    public Photo getPhoto(int i){
        return photos[i];
    }

    public static Photo[] getListPhoto() {
        return listPhoto;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView image ;
        private TextView caption ;

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            image = image;
        }

        public TextView getCaption() {
            return caption;
        }

        public void setCaption(TextView caption) {
            caption = caption;
        }

        public PhotoViewHolder(View itemview) {
            super(itemview);
            this.image = itemview.findViewById(R.id.image);
            this.caption = itemview.findViewById(R.id.textView);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
