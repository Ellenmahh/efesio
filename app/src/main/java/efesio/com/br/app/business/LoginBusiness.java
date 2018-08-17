package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import efesio.com.br.app.Login;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;


/**
 * Created by ELLEN on 17/08/2017.
 */
public class LoginBusiness extends AbstractBusiness<Login> {
    public LoginBusiness(Context context) {
        super(Service.ACCOUNT, context);
    }

    @Override
    protected String getPath() {
        return "usuario";
    }

    @Override
    public TypeReference<Login> getTypeReference() {
        return new TypeReference<Login>() {};
    }

    public Request<Login> login(String email, String senha){
        return new Request<>(new TypeReference<Login>() {}, getContext())
                .setService(getService())
                .setUri(getPath()+"/login")
                .putParam("login", email)
                .putParam("pass", senha)
                .putParam("conta", 3)
                .setMethod(Request.Method.POST);
    }

    public Request<Boolean> verificar(String email, String senha, String conta){
        return new Request<>(new TypeReference<Boolean>() {}, getContext())
                .setService(getService())
                .setUri(getPath()+"/login")
                .putParam("login", email)
                .putParam("pass", senha)
               //.putParam("conta", conta)
                .setMethod(Request.Method.GET);
    }

}
