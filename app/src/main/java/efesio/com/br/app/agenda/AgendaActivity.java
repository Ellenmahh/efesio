package efesio.com.br.app.agenda;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.AgendaBusiness;
import efesio.com.br.app.entities.Agenda;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;

public class AgendaActivity  extends ActivityBase
        implements Request.OnResult<List<Agenda>>, Request.OnError, Request.OnStart, Request.OnFinish {
    private String data;
    private RecyclerView mRecyclerView;
    private AgendaAdapter adapter = new AgendaAdapter(this );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        Toolbar toolbar =  findViewById(R.id.toolbarAg);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerViewAgenda);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);

        final CollapsibleCalendar collapsibleCalendar = findViewById(R.id.collapsibleCalendarView);
        Calendar today=new GregorianCalendar();
        collapsibleCalendar.addEventTag(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH));
        today.add(Calendar.DATE,1);
//        collapsibleCalendar.addEventTag(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH), Color.BLUE);
        if (data == null){
            if (collapsibleCalendar.getSelectedDay().getMonth() < 10 && collapsibleCalendar.getSelectedDay().getDay() < 10 ){
                data =  collapsibleCalendar.getSelectedDay().getYear()+"-0"+
                        collapsibleCalendar.getSelectedDay().getMonth()+"-0"+
                        collapsibleCalendar.getSelectedDay().getDay();
            }
            if (collapsibleCalendar.getSelectedDay().getMonth() < 10 ){
                data =  collapsibleCalendar.getSelectedDay().getYear()+"-0"+
                        collapsibleCalendar.getSelectedDay().getMonth()+"-"+
                        collapsibleCalendar.getSelectedDay().getDay();
            }
            if (collapsibleCalendar.getSelectedDay().getDay() < 10 ){
                data =  collapsibleCalendar.getSelectedDay().getYear()+"-"+
                        collapsibleCalendar.getSelectedDay().getMonth()+"-0"+
                        collapsibleCalendar.getSelectedDay().getDay();
            }
        }
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                String dia = null, mes = null;
                Day day = collapsibleCalendar.getSelectedDay();
                if (day.getMonth() < 10) {
                    mes = "0"+(day.getMonth()+1);
                }
                if (day.getDay() < 10){
                    dia = "0"+day.getDay();
                    data = day.getYear()+"-"+mes+"-"+dia;
                }else{
                    data = day.getYear()+"-"+mes+"-"+day.getDay();
                }

                 Toast.makeText(AgendaActivity.this, "clicouu "+data, Toast.LENGTH_SHORT).show();

                agenda();
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
        agenda();
    }

    private void agenda(){
        System.out.println("daata " + data);
        new AgendaBusiness(this)
                .agenda(data)
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();
    }
    @Override
    public void onStart(String tag) {
      //  loading(true);
        Toast.makeText(this, "Pesquisando ", Toast.LENGTH_LONG).show();

    }
    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        alert("Erro ao procurar eventos, tente novamente mais tarde.");
    }
    @Override
    public void onResult(String tag, NixResponse<List<Agenda>> res) {
        if (res.getStatus() != 200){
           // alert(res.getMessage());
            Toast.makeText(this,  res.getMessage(), Toast.LENGTH_LONG).show();
        }

        adapter.setItems(res.getEntity());

        System.out.println("getMessage ============= "+res.getMessage());
        System.out.println("HEADERS ============= "+res.getHeaders());
        System.out.println("ENTITY ============= "+res.getEntity().size());

//        if (res.getEntity().size() == 0){
//            vazio_ag.setVisibility(View.VISIBLE);
//        }
    }
    @Override
    public void onFinish(String tag) {
       // loading(false);
    }
}
