package efesio.com.br.app.rest.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import efesio.com.br.app.BuildConfig;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.json.Json;
import efesio.com.br.app.util.RuntimeValues;


/**
 * Created by otavi on 09/01/2017.
 */

public class VolleyRequest<T> extends JsonRequest<NixResponse<T>> {

    private static final int TIPO_JSON = 0;
    private static final int TIPO_PARAM = 1;
    private static final int TIPO_NULL = 2;
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE_JSON = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private static final String PROTOCOL_CONTENT_TYPE_FORM = String.format("application/x-www-form-urlencoded; charset=%s", PROTOCOL_CHARSET);

    private HashMap<String, String> postParams;
    private String body;
    private int tipoReq;
    private TypeReference<T> tClass;

    public VolleyRequest(int method,
                         String url,
                         String jsonRequest,
                         final Response.Listener<NixResponse<T>> listener,
                         final Response.ErrorListener errorListener,
                         TypeReference<T> tClass) {
        super(method, url, jsonRequest, listener, errorListener);
        this.body =  jsonRequest;
        this.tipoReq = TIPO_JSON;
        this.tClass = tClass;
    }

    public VolleyRequest(int method,
                         String url,
                         HashMap<String, String> postParams,
                         final Response.Listener<NixResponse<T>> listener,
                         final Response.ErrorListener errorListener,
                         TypeReference<T> tClass) {
        super(method, url, null, listener, errorListener);
        this.postParams = postParams;
        this.tipoReq = TIPO_PARAM;
        this.tClass = tClass;
    }

    public VolleyRequest(int method,
                         String url,
                         final Response.Listener<NixResponse<T>> listener,
                         final Response.ErrorListener errorListener,
                         TypeReference<T> tClass) {
        super(method, url, null, listener, errorListener);
        this.tipoReq = TIPO_NULL;
        this.tClass = tClass;
    }


    {
        setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<NixResponse<T>> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

            NixResponse<T> res = new NixResponse<>();
            res.setHeaders(response.headers);
            if (!jsonString.isEmpty()) {
                if(res.getHeaders().get("x-executed").equals("true"))
                    res.setEntity(Json.fromJson(jsonString, tClass));
            }
            res.setMessage(response.headers.get("x-message"));
            res.setStatus(response.statusCode);
            return Response.success(res,HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public String getBodyContentType() {
        if (postParams == null)
            return PROTOCOL_CONTENT_TYPE_JSON;
        else
            return PROTOCOL_CONTENT_TYPE_FORM;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return postParams;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("versionNumber", String.valueOf(BuildConfig.VERSION_CODE));
        headers.put("versionName", BuildConfig.VERSION_NAME);
        headers.put("userAgent", "1");
        headers.put("HASH", RuntimeValues.getToken());
        return headers;
    }

    @Override
    public byte[] getBody() {
        switch (tipoReq){
            case TIPO_JSON:{
                try {
                    return body == null ? null : body.getBytes(PROTOCOL_CHARSET);
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", body, PROTOCOL_CHARSET);
                    return null;
                }
            }

            case TIPO_PARAM:{
                Map<String, String> params = null;
                try {
                    params = getParams();
                } catch (AuthFailureError authFailureError) {
                    authFailureError.printStackTrace();
                }

                if (params != null && params.size() > 0) {
                    return encodeParameters(params, getParamsEncoding());
                }
                return null;
            }

            default: return null;
        }
    }

    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if(entry.getValue() == null)
                    continue;
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }
}
