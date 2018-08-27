package efesio.com.br.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import efesio.com.br.app.enums.UF;

public class EstadoAdapter extends ArrayAdapter<UF> {
    EstadoAdapter(@NonNull Context context, int resource, int spinnerText) {
        super(context, resource, spinnerText);
        addAll(UF.values());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);
    }


    private View initView(int position) {
        UF estado = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.estado_list, null);
        TextView textView =  v.findViewById(R.id.spinnerText);
        textView.setText(estado.getUf()+" - "+estado.getNome());
        System.out.println("estado -- "+estado.getUf()+" - "+estado.getNome());
        return v;

    }
}
