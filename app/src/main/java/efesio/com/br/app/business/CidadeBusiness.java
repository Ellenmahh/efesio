package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;

public class CidadeBusiness extends AbstractBusiness<String> {
        public CidadeBusiness(Context context) {
                super(Service.ACCOUNT, context);
        }

        @Override
        protected String getPath() {
                return "cidade";
        }

        @Override
        public TypeReference<String> getTypeReference() {
                return new TypeReference<String>(){};
        }

        public Request<List<String>> get (String uf){
                return new Request<>(new TypeReference<List<String>>(){}, getContext())
                        .setService(getService())
                        .setUri(getPath())
                        .putParam("uf", uf)
                        .setMethod(Request.Method.GET);
        }
}
