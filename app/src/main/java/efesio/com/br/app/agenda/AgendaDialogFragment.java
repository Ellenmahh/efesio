package efesio.com.br.app.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import efesio.com.br.app.R;

public class AgendaDialogFragment extends DialogFragment {
    TextView titulo_ag,descricao_ag, hora_ini_agenda,hora_fim_agenda, dia_agenda,mes_agenda, tipo_agenda;
    String titulo, dia, mes, horaInicio, horaFim, tipo, descricao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.agenda_details, container);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dia = getActivity().getIntent().getStringExtra("dia");
        mes = getActivity().getIntent().getStringExtra("mes");
        titulo = getActivity().getIntent().getStringExtra("titulo");
        horaInicio = getActivity().getIntent().getStringExtra("horaInicio");
        horaFim = getActivity().getIntent().getStringExtra("horaFim");
        descricao = getActivity().getIntent().getStringExtra("descricao");

        dia_agenda = view.findViewById(R.id.dia_agenda);
        mes_agenda = view.findViewById(R.id.mes_agenda);
        titulo_ag = view.findViewById(R.id.titulo_ag);
        hora_ini_agenda = view.findViewById(R.id.hora_ini_agenda);
        hora_fim_agenda = view.findViewById(R.id.hora_fim_agenda);
        descricao_ag = view.findViewById(R.id.descricao_ag);


        dia_agenda.setText(dia);
        mes_agenda.setText(mes);
        titulo_ag.setText(titulo);
        hora_ini_agenda.setText(horaInicio);
        hora_fim_agenda.setText(horaFim);
        descricao_ag.setText(descricao);
    }
}
