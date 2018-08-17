package efesio.com.br.app.rest;

/**
 * Created by otavi on 10/01/2017.
 */

public enum Service {
    ACCOUNT("http://localhost:8080/efesioapi/docs/?usuario=ellen&senha=123/",
            "http://localhost:8080/efesioapi/docs/?usuario=ellen&senha=123/",
            "http://localhost:8080/efesioapi/docs/?usuario=ellen&senha=123/");

    String url;
    String urlDev;
    String urlLocalDev;

    Service(String url, String urlDev, String urlLocalDev){
        this.url = url;
        this.urlDev = urlDev;
        this.urlLocalDev = urlLocalDev;
    }

    @Override
    public String toString() {
        return urlDev;
    }

}
