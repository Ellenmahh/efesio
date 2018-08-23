package efesio.com.br.app.rest;

import android.content.Context;
import android.util.Log;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import efesio.com.br.app.BuildConfig;
import efesio.com.br.app.rest.json.Json;
import efesio.com.br.app.rest.volley.VolleyRequest;


/**
 * Created by otavi on 09/01/2017.
 */

public class Request<T> implements Response.Listener<NixResponse<T>>, Response.ErrorListener, VolleyRequest.FinishedListener{

    public enum Method{
        POST, GET
    }
    public interface OnStart {
        public void onStart(String tag);
    }
    public interface OnError {
        public void onError(String tag, Exception e);
    }
    public interface OnResult<T> {
        public void onResult(String tag, NixResponse<T> res);
    }
    public interface OnFinish {
        public void onFinish(String tag);
    }

    private static RequestQueue queue;

    private static RequestQueue getQueue(Context context) {
        if (queue == null)
            queue = Volley.newRequestQueue(context);
        return queue;
    }

    public Request(TypeReference<T> tClass, Context context) {
        this.context = context;
        this.tClass = tClass;
    }

    private Method method;
    private Context context;
    private TypeReference<T> tClass;
    private Service service;
    private String uri;
    private String tag;
    private OnResult<T> onResult;
    private OnError onError;
    private OnStart onStart;
    private OnFinish onFinish;
    private HashMap<String, String> params;
    private HashMap<String, String> headers;
    private String body;
    private VolleyRequest<T> request;

    public String getUri() {
        return uri;
    }

    public Service getService() {
        return service;
    }

    public String getTag() {
        return tag;
    }

    public OnResult<T> getOnResult() {
        return onResult;
    }

    public OnError getOnError() {
        return onError;
    }

    public OnStart getOnStart() {
        return onStart;
    }

    public OnFinish getOnFinish() {
        return onFinish;
    }

    public String getBody() {
        return body;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public Method getMethod() {
        return method;
    }



    public Request<T> setMethod(Method method) {
        this.method = method;
        return this;
    }

    public Request<T> setService(Service service) {
        this.service = service;
        return this;
    }

    public Request<T> setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public Request<T> setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Request<T> setOnResult(OnResult<T> onResult) {
        this.onResult = onResult;
        return this;
    }

    public Request<T> setOnError(OnError onError) {
        this.onError = onError;
        return this;
    }

    public Request<T> setOnStart(OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    public Request<T> setOnFinish(OnFinish onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public Request<T> putParam(String name, String value) {
        if (getBody() != null)
            throw new IllegalStateException("Body already present. You can only inform a body object, a set of body params or a set of post params.");

        if(value == null)
            return this;

        if (getParams() == null)
            this.params = new HashMap<>();
        getParams().put(name, value);
        return this;
    }

    public Request<T> putParam(String name, int value) {
        return putParam(name, String.valueOf(value));
    }

    public Request<T> setBody(JSONObject body) {
        if (getParams() != null)
            throw new IllegalStateException("Post Params already present. You can only inform a body object, a set of body params or a set of post params.");
        this.body = body == null ? null : body.toString();
        return this;
    }

    public Request<T> setBody(String body) {
        if (getParams() != null)
            throw new IllegalStateException("Post Params already present. You can only inform a body object, a set of body params or a set of post params.");
        this.body = body;
        return this;
    }

    public <R> Request<T> setBody(R body) {
        if (getParams() != null)
            throw new IllegalStateException("Post Params already present. You can only inform a body object, a set of body params or a set of post params.");
        try {
            this.body = Json.toJson(body);
        }catch (Exception e){
            if (getOnError()!= null)
                getOnError().onError(tag, e);

            if(BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }

        return this;
    }

    /**
     * Executa a request */
    public Request<T> fire() {
        if (method == null)
            throw new IllegalArgumentException("Method cannot be null");

        if (context == null)
            throw new IllegalStateException("Context cannot be null");

        if (uri == null)
            throw new IllegalStateException("URL cannot be null");

        try {
            String urlfinal = service == null ? uri : service + uri;

            Log.i("Requesting " + urlfinal, "Creating request");

            if (method == Method.POST) {
                if (getBody() != null) {
                    request = new VolleyRequest<>(com.android.volley.Request.Method.POST, urlfinal, getBody(), this, this, this, tClass);
                } else if (getParams() != null) {
                    request = new VolleyRequest<>(com.android.volley.Request.Method.POST, urlfinal, getParams(), this, this, this, tClass);
                }
            } else {
                urlfinal += encodeParameters(getParams());
                request = new VolleyRequest<>(com.android.volley.Request.Method.GET, urlfinal, this, this, this, tClass);
            }
            request.setTag(tag);


            if (getOnStart() != null)
                getOnStart().onStart(tag);

            if (BuildConfig.DEBUG) {
                if (getBody() != null) {
                    Log.i("Request [" + tag + "]", getBody());
                } else if (getParams() != null) {
                    Log.i("Request [" + tag + "]", getParams().toString());
                }
            }

            getQueue(context).add(request);
            return this;
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Erro na requisição", "Erro", e);
            return null;
        }
    }

    public void cancel(){
        if (request == null)
            return;
        if (request.isCanceled())
            return;
        request.cancel();
    }

    public static void cancelAll(String... tags){
        if (queue == null)
            return;
        for (String t : tags)
            queue.cancelAll(t);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        if (getOnError()!= null)
            getOnError().onError(tag, error);

        if(BuildConfig.DEBUG) {
            error.printStackTrace();
        }

        onFinish(tag);
    }

    @Override
    public void onResponse(NixResponse<T> response) {
        if(BuildConfig.DEBUG) {
            Log.i("Response ["+tag+"]", response.toString());
        }
        try {
            if (getOnResult() != null) {
                getOnResult().onResult(tag, response);
            }
        }catch (Exception e){
            if (getOnError()!= null)
                getOnError().onError(tag, e);
            else
                e.printStackTrace();
        }
    }

    @Override
    public void onFinish(String tag) {
        if(BuildConfig.DEBUG) {
            Log.i("Request ["+tag+"]", "Finished");
        }
        if (getOnFinish() != null)
            getOnFinish().onFinish(tag);
    }

    private String encodeParameters(Map<String, String> params) {
        StringBuilder encodedParams = new StringBuilder();
        encodedParams.append("?");
        if (params != null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(entry.getKey());
                encodedParams.append('=');
                encodedParams.append(entry.getValue());
                encodedParams.append('&');
            }
        }
        return encodedParams.toString();
    }
}
