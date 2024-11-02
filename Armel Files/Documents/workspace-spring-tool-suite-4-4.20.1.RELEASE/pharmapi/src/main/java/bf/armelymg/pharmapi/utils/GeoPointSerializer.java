package bf.armelymg.pharmapi.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.cloud.firestore.GeoPoint;

import java.io.IOException;


@JsonSerialize(using = GeoPointSerializer.class)
public class GeoPointSerializer extends JsonSerializer<GeoPoint> {
    @Override
    public void serialize(GeoPoint value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("latitude", value.getLatitude());
        gen.writeNumberField("longitude", value.getLongitude());
        gen.writeEndObject();
    }
}
