package com.dd2480.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;

public class LCMDeserializer extends JsonDeserializer<LCM> {

    @Override
    public LCM deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        String[][] stringMatrix = jsonParser.readValueAs(String[][].class);

        ArrayList<ArrayList<Connector>> matrix = new ArrayList<>();

        for (String[] row : stringMatrix) {
            ArrayList<Connector> connectorRow = new ArrayList<>();
            for (String value : row) {
                connectorRow.add(Connector.fromString(value));
            }
            matrix.add(connectorRow);
        }

        return new LCM(matrix);
    }
}
