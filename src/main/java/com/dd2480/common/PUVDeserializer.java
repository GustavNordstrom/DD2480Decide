package com.dd2480.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PUVDeserializer extends JsonDeserializer<PUV> {

    @Override
    public PUV deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        List<Boolean> puvList = new ArrayList<>();
        Boolean[] booleanArray = jsonParser.readValueAs(Boolean[].class);

        for (Boolean value : booleanArray) {
            puvList.add(value);
        }

        return new PUV(puvList);
    }
}
