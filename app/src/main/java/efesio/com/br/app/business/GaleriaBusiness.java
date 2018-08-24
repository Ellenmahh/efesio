package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class GaleriaBusiness extends AbstractBusiness<String> {
public GaleriaBusiness(Context context) {
        super(Service.ACCOUNT, context);
        }

@Override
protected String getPath() {
        return "empresa/galeria";
        }

@Override
public TypeReference<String> getTypeReference() {
        return new TypeReference<String>(){};
        }

        public Request<List<String>> galeria (){
        return new Request<>(new TypeReference<List<String>>(){}, getContext())
        .setService(getService())
        .setUri(getPath())
        .setMethod(Request.Method.GET);
        }
}
