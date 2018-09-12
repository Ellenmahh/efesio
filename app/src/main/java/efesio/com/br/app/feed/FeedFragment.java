package efesio.com.br.app.feed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import efesio.com.br.app.R;

public class FeedFragment extends Fragment {
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    FeedAdapter feedAdapter;

    public static FeedFragment getInstance(){
        System.out.println("Criou o feed");
        FeedFragment f = new FeedFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        System.out.println("Criou a view do feed ");

        View v =  inflater.inflate(R.layout.fragment_feed, parent, false);

        mRecycleView =  v.findViewById(R.id.recyclerView);
        feedAdapter = new FeedAdapter();
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(feedAdapter);


        return v;

    }
}
