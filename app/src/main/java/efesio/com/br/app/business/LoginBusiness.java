package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;

import efesio.com.br.app.entities.Login;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;


/**
 * Created by ELLEN on 17/08/2017.
 */
public class LoginBusiness extends AbstractBusiness<Login> {
    public LoginBusiness(Context context) {
        super(Service.EFESIO, context);
    }

    @Override
    protected String getPath() {
        return "membro/login";
    }

    @Override
    public TypeReference<Login> getTypeReference() {
        return new TypeReference<Login>() {};
    }

    public Request<Login> login(String email, String senha){
//        System.out.println("login_user --- "+email);
//        System.out.println("password_user "+senha);
        return new Request<>(getTypeReference(), getContext())
                .setService(getService())
                .setUri(getPath())
                .putParam("email", email)
                .putParam("senha", senha)
                .setMethod(Request.Method.POST);

    }

}
