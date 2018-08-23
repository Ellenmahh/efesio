package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import efesio.com.br.app.entities.Aniversariante;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class AniversarianteBusiness extends AbstractBusiness<Aniversariante> {
    public AniversarianteBusiness(Context context) {
        super(Service.ACCOUNT, context);
    }

    @Override
    protected String getPath() {
        return "membro";
    }

    @Override
    public TypeReference<Aniversariante> getTypeReference() {
        return new TypeReference<Aniversariante>(){};
    }

    public Request<List<Aniversariante>> aniversariantes (String mesNascimento){
        return new Request<>(new TypeReference<List<Aniversariante>>(){}, getContext())
                .setService(getService())
                .setUri(getPath())
//                .putParam("nome", nome)
//                .putParam("nascimento", dtNasc)
                .putParam("mesNascimento", mesNascimento)
//                .putParam("urlFoto", foto)
                .setMethod(Request.Method.GET);
    }
}
