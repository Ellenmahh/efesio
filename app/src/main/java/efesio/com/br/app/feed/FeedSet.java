package efesio.com.br.app.feed;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.FeedItem;

public class FeedSet {
    private final static FeedItem[] sfeedItems ={
            new FeedItem( R.drawable.eventos,  "Galeria", FeedItem.Tipo.GALERIA),
            new FeedItem( R.drawable.schedule, "Veja a agenda da igreja para este mês", FeedItem.Tipo.AGENDA),
            new FeedItem( R.drawable.events,  "Veja os eventos da sua igreja", FeedItem.Tipo.EVENTOS),
            new FeedItem( R.drawable.aniver,  "Veja os aniversariantes do mês !", FeedItem.Tipo.ANIVERSARIANTE)

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
