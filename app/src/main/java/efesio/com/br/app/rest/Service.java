package efesio.com.br.app.rest;

/**
 * Created by Ellen on 17/08/2018.
 */

public enum Service {
//    ACCOUNT("http://efesioapi.azurewebsites.net/api/",
//            "http://efesioapi.azurewebsites.net/api/",
//            "http://efesioapi.azurewebsites.net/api/");
//
 ACCOUNT("http://192.168.0.118:8080/efesioapi/api/",
            "http://192.168.0.118:8080/efesioapi/api/",
            "http://192.168.0.118:8080/efesioapi/api/");

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
