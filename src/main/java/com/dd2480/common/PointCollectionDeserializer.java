package com.dd2480.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.List;

public class PointCollectionDeserializer extends JsonDeserializer<PointCollection> {

    @Override
    public PointCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        List<Point> points = jsonParser.readValueAs(List.class);

        PointCollection pointCollection = new PointCollection();
        for (Object obj : points) {
            if (obj instanceof java.util.LinkedHashMap) {
                java.util.LinkedHashMap<?, ?> map = (java.util.LinkedHashMap<?, ?>) obj;
                double x = ((Number) map.get("x")).doubleValue();
                double y = ((Number) map.get("y")).doubleValue();
                pointCollection.addPoint(new Point(x, y));
            }
        }

        return pointCollection;
    }
}
