package efesio.com.br.app.business;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.rest.Service;


/**
 * Created by Ellen on 17/08/2018.
 */

public abstract class AbstractBusiness<T> {

    public static final String TAG_CADASTRAR = "TAG_CADASTRAR";
    public static final String TAG_ALTERAR = "TAG_ALTERAR";
    public static final String TAG_CONSULTAR = "TAG_CONSULTAR";
    public static final String TAG_CONTAR = "TAG_CONTAR";
    public static final String TAG_LISTAR = "TAG_LISTAR";

    private Context context;
    private Service service;

    public AbstractBusiness(Service service, Context context){
        this.service = service;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    protected abstract String getPath();

    public Request<T> cadastrar(T input){
        return new Request<T>(getTypeReference(),context)
                .setMethod(Request.Method.POST)
                .setTag(TAG_CADASTRAR)
                .setService(service)
                .setUri(getPath())
                .setBody(input);
    }

    public Request<T> alterar(T input){
        return new Request<T>(getTypeReference(),context)
                .setMethod(Request.Method.POST)
                .setTag(TAG_ALTERAR)
                .setService(service)
                .setUri(getPath()+"/alterar")
                .setBody(input);
    }

    public Request<T> consultar(Object pk){
       return new Request<T>(getTypeReference(),context)
                .setMethod(Request.Method.GET)
                .setTag(TAG_CONSULTAR)
                .setService(service)
                .setUri(getPath()+"/consultar")
                .putParam("pk", String.valueOf(pk));
    }

//    public void consultar(F filter){
//        new Request<T>(context)
//                .setTag(TAG_CONSULTAR)
//                .setService(service)
//                .setUri(getPath()+"/consultar")
//                .setBody(filter.toString())
//                .setMethod(Request.Method.POST)
//                .fire();
//    }
//
//    public void contar(F filter){
//        new Request<T>(context)
//                .setTag(TAG_CONTAR)
//                .setService(service)
//                .setUri(getPath()+"/contar")
//                .setBody(filter.toString())
//                .setOnStart(onStart)
//                .setOnFinish(onFinish)
//                .setOnError(onError)
//                .setOnResult(onResultCount)
//                .setMethod(Request.Method.POST)
//                .fire();
//    }
//
//    public void listar(F filter){
//        new Request<T>(context)
//                .setTag(TAG_LISTAR)
//                .setService(service)
//                .setUri(getPath()+"/listar")
//                .setBody(filter.toString())
//                .setOnStart(onStart)
//                .setOnFinish(onFinish)
//                .setOnError(onError)
//                .setOnResult(onResultList)
//                .setMethod(Request.Method.POST)
//                .fire();
//    }

    public abstract TypeReference<T> getTypeReference();
}
