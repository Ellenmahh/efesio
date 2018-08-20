package efesio.com.br.app.feed;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.FeedItem;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    private FeedSet mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public FeedAdapter(FeedSet album) {
        mDataset = album;
    }
    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layout = 0;
        FeedItem.Tipo tipo = FeedItem.Tipo.getByType(viewType);
        if (tipo == null){
            throw new IllegalArgumentException("View Type cannot be null");
        }
        switch (tipo){
            case AGENDA:layout = R.layout.fragment_feed_item_agenda;break;
            case EVENTOS:layout = R.layout.fragment_feed_item_evento;break;
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new FeedViewHolder(v, tipo);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder viewHolder, int position) {
        FeedItem feedItem = mDataset.getItem(position);
        viewHolder.fill(feedItem);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.getItem(position).getTipo().getType();
    }
}
