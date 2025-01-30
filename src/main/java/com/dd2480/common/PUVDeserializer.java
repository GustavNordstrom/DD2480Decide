package com.dd2480.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom deserializer for the PUV (Preliminary Unlocking Vector) class.
 * This class is responsible for converting a JSON array of boolean values into
 * a PUV object.
 */
public class PUVDeserializer extends JsonDeserializer<PUV> {

    /**
     * Deserializes a JSON array of boolean values into a PUV object.
     *
     * @param jsonParser             JSON parser to read the input JSON data.
     * @param deserializationContext Context for the deserialization process.
     * @return A PUV object containing a list of boolean values.
     * @throws IOException             If an error occurs while processing the JSON
     *                                 input.
     * @throws JsonProcessingException If the JSON structure is invalid.
     */
    @Override
    public PUV deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        // Initialize an empty list to store boolean values
        List<Boolean> puvList = new ArrayList<>();

        // Read the JSON array as a Boolean array
        Boolean[] booleanArray = jsonParser.readValueAs(Boolean[].class);

        // Convert the Boolean array into a List
        for (Boolean value : booleanArray) {
            puvList.add(value);
        }

        // Return a new PUV object containing the deserialized boolean values
        return new PUV(puvList);
    }
}
