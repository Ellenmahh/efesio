package efesio.com.br.app.feed;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.FeedItem;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    private List<FeedItem> items = new ArrayList<>();

    public FeedAdapter() {
        this.items.addAll(Arrays.asList(
                new FeedItem(R.drawable.eventos, "Galeria", FeedItem.Tipo.GALERIA, 0),
                new FeedItem(R.drawable.schedule, "Agora você pode ver a agenda mensal e \n diária da sua igreja.", FeedItem.Tipo.AGENDA, 1),
                new FeedItem(R.drawable.events, "Fique por dentro dos eventos deste mês!", FeedItem.Tipo.EVENTOS, 2),
                new FeedItem(R.drawable.aniver, "Veja a lista de aniversariantes do mês.", FeedItem.Tipo.ANIVERSARIANTE ,3)
        ));
    }


    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = 0;
        final FeedItem.Tipo tipo = FeedItem.Tipo.getByType(viewType);
        if (tipo == null){
            throw new IllegalArgumentException("View Type cannot be null");
        }
        switch (tipo){
            case GALERIA:layout = R.layout.fragment_feed_item_galeria;break;
            case AGENDA:layout = R.layout.fragment_feed_item_agenda;break;
            case EVENTOS:layout = R.layout.fragment_feed_item_evento;break;
            case ANIVERSARIANTE:layout = R.layout.fragment_feed_item_aniver;break;
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new FeedViewHolder(v, tipo , new FeedViewHolder.DisplayCallback() {
            @Override
            public void canDisplay(FeedItem item, boolean loaded) {
                if(!loaded){
                    items.remove(item.getPosition());
                }
//                else{
//                    items.add(item.getPosition(), item);
//                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder viewHolder, int position) {
        FeedItem feedItem = items.get(position);
        viewHolder.fill(feedItem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getTipo().getType();
    }
}
