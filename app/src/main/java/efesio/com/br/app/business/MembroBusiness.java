package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import efesio.com.br.app.entities.IgrejaMembro;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class MembroBusiness extends AbstractBusiness<IgrejaMembro> {
    public MembroBusiness(Context context) {
        super(Service.ACCOUNT, context);
    }

    @Override
    protected String getPath() {
        return "membro";
    }

    @Override
    public TypeReference<IgrejaMembro> getTypeReference() {
        return new TypeReference<IgrejaMembro>(){};
    }

    public Request<List<IgrejaMembro>> buscarIgreja (String mesNascimento){
        return new Request<>(new TypeReference<List<IgrejaMembro>>(){}, getContext())
                .setService(getService())
                .setUri(getPath()+"/buscarIgreja")
                .putParam("cpf", mesNascimento)
                .setMethod(Request.Method.GET);
    }
}
