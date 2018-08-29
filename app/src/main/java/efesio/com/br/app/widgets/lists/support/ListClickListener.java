package efesio.com.br.app.widgets.lists.support;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import efesio.com.br.app.R;


/**
 * Created by Administrador on 11/12/2015.
 */
public class ListClickListener {
    private final RecyclerView mRecyclerView;
    private ListClickListener.OnItemClickListener mOnItemClickListener;
    private ListClickListener.OnItemLongClickListener mOnItemLongClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
        }
    };
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener mAttachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    };

    private ListClickListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.list_click_listener, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    public static ListClickListener addTo(RecyclerView view) {
        ListClickListener support = (ListClickListener) view.getTag(R.id.list_click_listener);
        if (support == null) {
            support = new ListClickListener(view);
        }
        return support;
    }

    public static ListClickListener removeFrom(RecyclerView view) {
        ListClickListener support = (ListClickListener) view.getTag(R.id.list_click_listener);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public ListClickListener setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    public ListClickListener setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.list_click_listener, null);
    }

    public interface OnItemClickListener {

        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {

        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}