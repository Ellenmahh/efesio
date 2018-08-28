package efesio.com.br.app.business;

import android.content.Context;
import android.widget.Spinner;

import com.fasterxml.jackson.core.type.TypeReference;

import efesio.com.br.app.entities.Login;
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

    public Request<Login> login(String email, String senha, Spinner conta){
        return new Request<>(getTypeReference(), getContext())
                .setService(getService())
                .setUri(getPath()+"/login")
                .putParam("login", email)
                .putParam("pass", senha)
                .putParam("conta", String.valueOf(conta))
                .setMethod(Request.Method.POST);

    }

}
