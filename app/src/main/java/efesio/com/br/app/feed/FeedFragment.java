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
    FeedSet feedSet;
    FeedAdapter feedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_feed, parent, false);
        feedSet = new FeedSet();
        feedAdapter = new FeedAdapter(feedSet);
        mLayoutManager = new LinearLayoutManager(this.getContext());

        mRecycleView =  v.findViewById(R.id.recyclerView);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(feedAdapter);
        return v;

    }
}
