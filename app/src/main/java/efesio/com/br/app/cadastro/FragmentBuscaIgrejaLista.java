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
import efesio.com.br.app.util.RuntimeValues;
import efesio.com.br.app.widgets.lists.support.ListClickListener;

public class FragmentBuscaIgrejaLista extends Fragment {
    public interface BuscaIgrejaOnSelect{
        void onSelect(IgrejaMembro item);
    }

    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManager;
    private IgrejaAdapter adapter;
    private BuscaIgrejaOnSelect onSelect;

    private List<IgrejaMembro> itens;

    public static FragmentBuscaIgrejaLista getInstance(List<IgrejaMembro> itens, BuscaIgrejaOnSelect onSelect){
        FragmentBuscaIgrejaLista f = new FragmentBuscaIgrejaLista();
        f.onSelect = onSelect;
        f.itens = itens;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_busca_igreja_lista, parent, false);
        adapter = new IgrejaAdapter(getContext(), itens);
        mLayoutManager = new LinearLayoutManager(this.getContext());

        mRecycleView =  v.findViewById(R.id.igreja_rv);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(adapter);

        ListClickListener listener = ListClickListener.addTo(mRecycleView);
        listener.setOnItemClickListener(new ListClickListener.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                    System.out.println("Item clicado");
                    onSelect.onSelect(adapter.getItem(position));

                    RuntimeValues.setNomeIgreja(adapter.getItem(position).getNomeIgreja());
                    RuntimeValues.setFotoIgreja(adapter.getItem(position).getFotoIgreja());
                }
            });

        return v;

    }
}
