package efesio.com.br.app.feed;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.FeedItem;

public class FeedSet {
    private final static FeedItem[] sfeedItems ={
            new FeedItem( R.drawable.eventos,  "Galeria", FeedItem.Tipo.GALERIA),
            new FeedItem( R.drawable.agenda, "Agenda da Igreja", FeedItem.Tipo.AGENDA),
            new FeedItem( R.drawable.eventos,  "Eventos", FeedItem.Tipo.EVENTOS)

    };

    private final FeedItem[] feedItems;

    public FeedSet(){
        this.feedItems = sfeedItems;
    }

    public int size(){
        return this.feedItems.length;
    }

    public FeedItem getItem(int i){
        return feedItems[i];
    }

    public FeedItem[] getFeedItems() {
        return feedItems;
    }

}
