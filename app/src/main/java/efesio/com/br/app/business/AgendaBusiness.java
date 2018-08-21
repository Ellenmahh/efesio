package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import efesio.com.br.app.entities.Agenda;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class AgendaBusiness extends AbstractBusiness<Agenda> {
    public AgendaBusiness(Context context) {
        super(Service.ACCOUNT, context);
    }

    @Override
    protected String getPath() {
        return "agenda";
    }

    @Override
    public TypeReference<Agenda> getTypeReference() {
        return new TypeReference<Agenda>() {};
    }

    public Request<List<Agenda>> agenda (String date){
        return new Request<>(new TypeReference<List<Agenda>>(){}, getContext())
                .setService(getService())
                .setUri(getPath())
                .putParam("dataIni", date)
                .putParam("dataFim", date)
                .setMethod(Request.Method.GET);
    }
}
