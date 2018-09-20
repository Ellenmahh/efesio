package efesio.com.br.app.feed;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import efesio.com.br.app.R;

public class FeedFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    FeedAdapter feedAdapter;
    SwipeRefreshLayout swipeLayout;

    public static FeedFragment getInstance(){
        FeedFragment f = new FeedFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_feed, parent, false);

        mRecycleView =  v.findViewById(R.id.recyclerView);
        feedAdapter = new FeedAdapter();
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(feedAdapter);
        swipeLayout = v.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(false);
                mRecycleView.setAdapter(feedAdapter);
            }
        });
        return v;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                swipeLayout.setRefreshing(true);
                mRecycleView.setAdapter(feedAdapter);
            }
        }, 3000);
    }


}
