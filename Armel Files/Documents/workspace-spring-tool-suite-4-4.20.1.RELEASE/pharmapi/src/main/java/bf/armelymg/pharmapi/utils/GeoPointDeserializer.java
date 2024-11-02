package bf.armelymg.pharmapi.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.cloud.firestore.GeoPoint;

import java.io.IOException;

public class GeoPointDeserializer extends JsonDeserializer<GeoPoint> {
    @Override
    public GeoPoint deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var node = p.getCodec().readTree(p);
        double latitude = node.get("latitude").traverse().getValueAsDouble();
        double longitude = node.get("longitude").traverse().getValueAsDouble();
        return new GeoPoint(latitude, longitude);
    }
}
