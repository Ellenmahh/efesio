package efesio.com.br.app.rest.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by otavi on 15/05/2017.
 */

public class Json{
    private static ObjectMapper instance;
    public static ObjectMapper getInstance(){
        if (instance == null) {
            instance = new ObjectMapper();
            instance.registerModule(new LocalDateTimeModule());
            instance.registerModule(new LocalDateModule());
            instance.registerModule(new JSONObjectModule());
            instance.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            instance.configure (SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        }
        return instance;
    }

    public static <T> String toJson(T object) throws JsonProcessingException {
        return getInstance().writeValueAsString(object);
    }

    public static <T> T fromJson(JSONObject object, Class<T> tClass) throws IOException {
        return getInstance().readValue(object.toString(), tClass);
    }

    public static <T> T fromJson(String object, Class<T> tClass) throws IOException {
        return getInstance().readValue(object, tClass);
    }

    public static <T> T fromJson(JSONObject object, TypeReference<T> tClass) throws IOException {
        return getInstance().readValue(object.toString(), tClass);
    }

    public static <T> T fromJson(String object, TypeReference<T> tClass) throws IOException {

        return getInstance().readValue(object, tClass);
    }
}
