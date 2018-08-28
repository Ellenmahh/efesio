package efesio.com.br.app.cadastro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.cadastro.list.IgrejaAdapter;
import efesio.com.br.app.entities.IgrejaMembro;

public class FragmentBuscaIgrejaLista extends Fragment {
    public interface BuscaIgrejaOnSelect{
        void onSelect(IgrejaMembro item);
    }

    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManager;
    private IgrejaAdapter adapter;
    private BuscaIgrejaOnSelect onSelect;

    public static FragmentBuscaIgrejaLista getInstance(List<IgrejaMembro> itens, BuscaIgrejaOnSelect onSelect){
        FragmentBuscaIgrejaLista f = new FragmentBuscaIgrejaLista();
        f.onSelect = onSelect;
        f.adapter.setItems(itens);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_busca_igreja_lista, parent, false);
        adapter = new IgrejaAdapter(getContext());
        mLayoutManager = new LinearLayoutManager(this.getContext());

        mRecycleView =  v.findViewById(R.id.igreja_rv);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(adapter);
        return v;

    }
}
