package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.DecimalFormat;

public class GsonUtil {
    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DecimalFormat.class, new DecimalFormatTypeAdapter());
        gson = builder.create();  
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, java.lang.reflect.Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
