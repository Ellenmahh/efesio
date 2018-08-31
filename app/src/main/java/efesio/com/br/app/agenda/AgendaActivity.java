package efesio.com.br.app.agenda;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

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
    private CollapsibleCalendar collapsibleCalendar;
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

        collapsibleCalendar = findViewById(R.id.collapsibleCalendarView);
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = collapsibleCalendar.getSelectedDay();
                LocalDate d = new LocalDate(day.getYear(), day.getMonth(), day.getDay());
                Toast.makeText(AgendaActivity.this, "clicouu "+d, Toast.LENGTH_SHORT).show();
                agenda(d);
            }

            @Override
            public void onItemClick(View v) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int position) {
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
        alert("Erro ao procurar agenda, tente novamente mais tarde."+e.getMessage());
    }
    @Override
    public void onResult(String tag, final NixResponse<List<Agenda>> res) {
        if (res.getStatus() != 201){
            Toast.makeText(this,  res.getMessage(), Toast.LENGTH_LONG).show();
        }
        adapter.setItems(res.getEntity());
        if(tag.equals("CARREGAR_BOLINHAS")){
            for(Agenda a : res.getEntity()) {
                LocalDate data = a.getData();
                collapsibleCalendar.addEventTag(data.getYear(), data.getMonthOfYear()-1, data.getDayOfMonth());
                System.out.println(data.getYear()+" - "+ data.toString("MMM")+" - "+data.getDayOfMonth());
            }
        }
        listener.setOnItemClickListener(new ListClickListener.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                System.out.println("clicado -- "+res.getEntity().get(position));
                List <String> x = new ArrayList<>();
                x.add(String.valueOf(getIntent().putExtra("dia", String.valueOf(res.getEntity().get(position).getData().getDayOfMonth()))));
                x.add(String.valueOf(getIntent().putExtra("mes", String.valueOf(res.getEntity().get(position).getData().toString("MMM").toUpperCase()))));
                x.add(String.valueOf(getIntent().putExtra("titulo", String.valueOf(res.getEntity().get(position).getTitulo()))));
                x.add(String.valueOf(getIntent().putExtra("horaInicio", String.valueOf(res.getEntity().get(position).getHora_inicial()))));
                x.add(String.valueOf(getIntent().putExtra("horaFim", String.valueOf(res.getEntity().get(position).getHora_termino()))));
                x.add(String.valueOf(getIntent().putExtra("descricao", String.valueOf(res.getEntity().get(position).getDescricao()))));
                System.out.println("clicado -- "+x);
                showEditDialog(x);
            }
        });
    }
    @Override
    public void onFinish(String tag) {
        loading(false);
    }
}
