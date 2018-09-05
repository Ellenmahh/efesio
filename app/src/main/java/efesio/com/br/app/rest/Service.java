package efesio.com.br.app.rest;

/**
 * Created by Ellen on 17/08/2018.
 */

public enum Service {

 EFESIO("http://192.168.0.121:8080/efesioapi/api/",
            "https://prompwebsa.blob.core.windows.net/efesio/");

    String url;
    String storage;

    Service(String url, String urlDev){
        this.url = url;
        this.storage = urlDev;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return url;
    }

    public String getStorage() {
        return storage;
    }
}
