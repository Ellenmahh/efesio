package efesio.com.br.app.agenda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.AgendaBusiness;
import efesio.com.br.app.entities.Agenda;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.RuntimeValues;
import efesio.com.br.app.widgets.lists.support.ListClickListener;

public class AgendaActivity  extends ActivityBase
        implements Request.OnResult<List<Agenda>>, Request.OnError, Request.OnStart, Request.OnFinish {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private AgendaAdapter adapter = new AgendaAdapter(this );
    private CalendarView calendario;
    private Toolbar toolbar;
    private ListClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        toolbar =  findViewById(R.id.toolbarAg);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        manager = new LinearLayoutManager(this);

        mRecyclerView = findViewById(R.id.recyclerViewAgenda);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);

        listener = ListClickListener.addTo(mRecyclerView);

        calendario = findViewById(R.id.calendario);
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                LocalDate d = new LocalDate(year, month, dayOfMonth);
                agenda(d);
            }
        });
        agenda(null);
    }

    private void showEditDialog(List<String> s) {
        FragmentManager fm = getSupportFragmentManager();
        AgendaDialogFragment editNameDialogFragment = new AgendaDialogFragment();
        editNameDialogFragment.show(fm, String.valueOf(s));
    }

    private void agenda(LocalDate data){
        new AgendaBusiness(this)
                .agenda(data, RuntimeValues.getIdEmpresa())
                .setTag(data == null ? "CARREGAR_BOLINHAS" : "LISTAR")
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();
    }
    @Override
    public void onStart(String tag) {
        loading(true);
//        Toast.makeText(this, "Pesquisando ", Toast.LENGTH_LONG).show();

    }
    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        alert("Erro ao procurar agenda, tente novamente mais tarde.");
    }
    @Override
    public void onResult(String tag, final NixResponse<List<Agenda>> res) {
        if (res.getStatus() != 201){
            alert(res.getMessage());
//            Toast.makeText(this,  res.getMessage(), Toast.LENGTH_LONG).show();
        }
        adapter.setItems(res.getEntity());
        if(tag.equals("CARREGAR_BOLINHAS")){
//            for(Agenda a : res.getEntity()) {
//                LocalDate data = a.getData();
//                collapsibleCalendar.addEventTag(data.getYear(), data.getMonthOfYear()-1, data.getDayOfMonth());
////                System.out.println(data.getYear()+" - "+ data.toString("MMM")+" - "+data.getDayOfMonth());
//            }
        }
        if (res.getEntity().size() == 0 ){
            alert("opsss... ", "Não há agenda para este mês.");
        }
        listener.setOnItemClickListener(new ListClickListener.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                System.out.println("clicado -- "+res.getEntity().get(position));
                List <String> x = new ArrayList<>();
                x.add(String.valueOf(getIntent().putExtra("dia", String.valueOf(res.getEntity().get(position).getData().getDayOfMonth()))));
                x.add(String.valueOf(getIntent().putExtra("mes", String.valueOf(res.getEntity().get(position).getData().toString("MMM").toUpperCase()))));
                x.add(String.valueOf(getIntent().putExtra("titulo", String.valueOf(res.getEntity().get(position).getTitulo()))));
                x.add(String.valueOf(getIntent().putExtra("horaInicio", String.valueOf(res.getEntity().get(position).getHoraInicio()))));
                x.add(String.valueOf(getIntent().putExtra("horaFim", String.valueOf(res.getEntity().get(position).getHoraTermino()))));
                x.add(String.valueOf(getIntent().putExtra("descricao", String.valueOf(res.getEntity().get(position).getDescricao()))));
                showEditDialog(x);
            }
        });
    }
    @Override
    public void onFinish(String tag) {
        loading(false);
    }
}
