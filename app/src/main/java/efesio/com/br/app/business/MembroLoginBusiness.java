package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import efesio.com.br.app.entities.IgrejaMembro;
import efesio.com.br.app.entities.MembroLogin;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class MembroLoginBusiness extends AbstractBusiness<MembroLogin> {
    public MembroLoginBusiness(Context context) {
        super(Service.EFESIO, context);
    }

    @Override
    protected String getPath() {
        return "membro/login";
    }

    @Override
    public TypeReference<MembroLogin> getTypeReference() {
        return new TypeReference<MembroLogin>(){};
    }

    public Request<MembroLogin> login(String email , String senha) {
        return new Request<>(getTypeReference(), getContext())
                .setService(getService())
                .setUri(getPath())
                .putParam("email", email)
                .putParam("senha", senha)
                .setMethod(Request.Method.POST);
    }

    public Request<List<IgrejaMembro>> buscarIgreja (String mesNascimento){
        return new Request<>(new TypeReference<List<IgrejaMembro>>(){}, getContext())
                .setService(getService())
                .setUri(getPath()+"/buscarIgreja")
                .putParam("cpf", mesNascimento)
                .setMethod(Request.Method.GET);
    }
}
