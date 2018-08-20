package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import efesio.com.br.app.entities.Agenda;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class AgendaBusiness extends AbstractBusiness<Agenda> {
    public AgendaBusiness(Service service, Context context) {
        super(service, context);
    }

    @Override
    protected String getPath() {
        return "agenda";
    }

    @Override
    public TypeReference<Agenda> getTypeReference() {
        return new TypeReference<Agenda>() {};
    }

    public Request<Agenda> agenda (String token){
        return new Request<>(getTypeReference(), getContext())
                .setService(getService())
                .setUri(getPath())
                .putParam("HASH", token)
                .setMethod(Request.Method.GET);
    }
}
