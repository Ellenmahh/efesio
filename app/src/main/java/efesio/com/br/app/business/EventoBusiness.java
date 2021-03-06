package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import org.joda.time.LocalDate;

import java.util.List;

import efesio.com.br.app.entities.Evento;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class EventoBusiness extends AbstractBusiness<Evento> {
    public EventoBusiness(Context context) {
        super(Service.EFESIO, context);
    }

    @Override
    protected String getPath() {
        return "app/evento";
    }

    @Override
    public TypeReference<Evento> getTypeReference() {
        return new TypeReference<Evento>(){};
    }

    public Request<List<Evento>> eventos (int id){
        return new Request<>(new TypeReference<List<Evento>>(){}, getContext())
                .setService(getService())
                .setUri(getPath())
                .putParam("_idIgreja",id)
                .putParam("dataIni", LocalDate.now().getYear() + LocalDate.now().getMonthOfYear() + LocalDate.now().getDayOfMonth())
                .putParam("dataFim", LocalDate.now().getYear() + LocalDate.now().getMonthOfYear() + LocalDate.now().getDayOfMonth())
                .setMethod(Request.Method.GET);
    }
}
