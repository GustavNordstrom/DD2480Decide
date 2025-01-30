package com.dd2480.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Custom deserializer for the LCM (Logical Connector Matrix).
 * This class is responsible for converting a JSON array of strings into an LCM
 * object
 * with a matrix of Connector enum values.
 */
public class LCMDeserializer extends JsonDeserializer<LCM> {

    /**
     * Deserializes a JSON array of string representations of logical connectors
     * into an LCM object.
     *
     * @param jsonParser             JSON parser for reading the input JSON data.
     * @param deserializationContext Context for the deserialization process.
     * @return LCM object containing a matrix of Connector enums.
     * @throws IOException             If an error occurs during JSON processing.
     * @throws JsonProcessingException If the JSON structure is invalid.
     */
    @Override
    public LCM deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        // Read the JSON data as a 2D array of strings
        String[][] stringMatrix = jsonParser.readValueAs(String[][].class);

        // Initialize an ArrayList to hold the converted matrix of Connector enums
        ArrayList<ArrayList<Connector>> matrix = new ArrayList<>();

        // Iterate through each row of the string matrix
        for (String[] row : stringMatrix) {
            ArrayList<Connector> connectorRow = new ArrayList<>();
            // Convert each string value to a Connector enum and add it to the row
            for (String value : row) {
                connectorRow.add(Connector.fromString(value));
            }
            // Add the processed row to the matrix
            matrix.add(connectorRow);
        }

        // Return a new LCM object constructed with the converted matrix
        return new LCM(matrix);
    }
}
