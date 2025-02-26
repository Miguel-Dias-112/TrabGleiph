package Utils;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class DecimalFormatTypeAdapter implements JsonSerializer<DecimalFormat>, JsonDeserializer<DecimalFormat> {
    @Override
    public JsonElement serialize(DecimalFormat src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toPattern());
    }

    @Override
    public DecimalFormat deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new DecimalFormat(json.getAsString());
    }
}
