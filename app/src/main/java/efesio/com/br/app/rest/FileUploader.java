package efesio.com.br.app.rest;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import efesio.com.br.app.BuildConfig;
import efesio.com.br.app.rest.volley.MultipartRequest;


/**
 * Created by Administrador on 17/08/2016.
 */
public class FileUploader {

    public static <T> void uploadFile(final String tag, final String url,
                                      final File file, final String partName,
                                      final Map<String, String> bodyParam,
                                      final Context context,
                                      final Response.Listener<String> resultDelivery,
                                      final Response.ErrorListener errorListener,
                                      MultipartRequest.MultipartProgressListener progListener) {

        uploadFile(tag,url,file,partName, new HashMap<String, String>(),bodyParam,context,resultDelivery,errorListener,progListener);
    }


    public static <T> void uploadFile(final String tag, final String url,
                                      final File file, final String partName,
                                      final Map<String, String> headerParams,
                                      final Map<String, String> bodyParam,
                                      final Context context,
                                      final Response.Listener<String> resultDelivery,
                                      final Response.ErrorListener errorListener,
                                      MultipartRequest.MultipartProgressListener progListener) {


        AZNetworkRetryPolicy retryPolicy = new AZNetworkRetryPolicy();

        MultipartRequest mr = new MultipartRequest(url, errorListener, resultDelivery, file, file.length(), bodyParam, headerParams,partName, progListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("versionNumber", String.valueOf(BuildConfig.VERSION_CODE));
                headers.put("versionName", BuildConfig.VERSION_NAME);
                headers.put("userAgent", "1");
                return headers;
            }
        };

        mr.setRetryPolicy(retryPolicy);
        mr.setTag(tag);

        Volley.newRequestQueue(context).add(mr);

    }

    static class AZNetworkRetryPolicy implements RetryPolicy{

        @Override
        public int getCurrentTimeout() {
            return 30000;
        }

        @Override
        public int getCurrentRetryCount() {
            return 2;
        }

        @Override
        public void retry(VolleyError error) throws VolleyError {
            error.printStackTrace();
        }
    }



}
