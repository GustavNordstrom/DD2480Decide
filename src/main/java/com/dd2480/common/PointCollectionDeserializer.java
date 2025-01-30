package com.dd2480.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.List;

/**
 * Custom deserializer for the PointCollection class.
 * This class is responsible for converting a JSON array of points into a
 * PointCollection object.
 */
public class PointCollectionDeserializer extends JsonDeserializer<PointCollection> {

    /**
     * Deserializes a JSON array of point objects into a PointCollection instance.
     *
     * @param jsonParser             JSON parser to read the input JSON data.
     * @param deserializationContext Context for the deserialization process.
     * @return A PointCollection object containing the list of deserialized Point
     *         objects.
     * @throws IOException             If an error occurs while processing the JSON
     *                                 input.
     * @throws JsonProcessingException If the JSON structure is invalid.
     */
    @Override
    public PointCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        // Read the JSON array as a generic List
        List<Point> points = jsonParser.readValueAs(List.class);

        // Initialize an empty PointCollection
        PointCollection pointCollection = new PointCollection();

        // Iterate through each entry in the list
        for (Object obj : points) {
            // Ensure that each object is a LinkedHashMap (JSON object representation in
            // Java)
            if (obj instanceof java.util.LinkedHashMap) {
                java.util.LinkedHashMap<?, ?> map = (java.util.LinkedHashMap<?, ?>) obj;
                // Extract "x" and "y" values from the map and convert them to double
                double x = ((Number) map.get("x")).doubleValue();
                double y = ((Number) map.get("y")).doubleValue();
                // Create a new Point and add it to the PointCollection
                pointCollection.addPoint(new Point(x, y));
            }
        }

        // Return the populated PointCollection object
        return pointCollection;
    }
}
