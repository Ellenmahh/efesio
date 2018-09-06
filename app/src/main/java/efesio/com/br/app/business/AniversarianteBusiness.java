package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import efesio.com.br.app.entities.Aniversariante;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class AniversarianteBusiness extends AbstractBusiness<Aniversariante> {
    public AniversarianteBusiness(Context context) {
        super(Service.EFESIO, context);
    }

    @Override
    protected String getPath() {
        return "app/aniversariantes";
    }

    @Override
    public TypeReference<Aniversariante> getTypeReference() {
        return new TypeReference<Aniversariante>(){};
    }

    public Request<List<Aniversariante>> aniversariantes (String mesNascimento, int id ){
        return new Request<>(new TypeReference<List<Aniversariante>>(){}, getContext())
                .setService(getService())
                .setUri(getPath())
                .putParam("_idIgreja",id)
                .putParam("mesNascimento", mesNascimento)
                .setMethod(Request.Method.GET);
    }
}
