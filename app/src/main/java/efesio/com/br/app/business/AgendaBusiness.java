package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import org.joda.time.LocalDate;

import java.util.List;

import efesio.com.br.app.entities.Agenda;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class AgendaBusiness extends AbstractBusiness<Agenda> {
    public AgendaBusiness(Context context) {
        super(Service.EFESIO, context);
    }

    @Override
    protected String getPath() {
        return "app/agenda";
    }

    @Override
    public TypeReference<Agenda> getTypeReference() {
        return new TypeReference<Agenda>() {};
    }

    public Request<List<Agenda>> agenda (LocalDate date, int id){
        return new Request<>(new TypeReference<List<Agenda>>(){}, getContext())
                .setService(getService())
                .setUri(getPath())
                .putParam("_idIgreja",id)
                .putParam("dataIni", date == null ? null : date.toString("yyyy-MM-dd"))
                .putParam("dataFim", date == null ? null : date.toString("yyyy-MM-dd"))
                .setMethod(Request.Method.GET);
    }
}
