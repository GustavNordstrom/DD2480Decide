package com.dd2480.inputoutput.impl;

import com.dd2480.inputoutput.InputData;
import com.dd2480.inputoutput.InputHandler;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class InputHandlerImpl implements InputHandler {
    private boolean isProcessed = false;  // Track if processInput has been called
    private InputData inputData;
    private String path;
    private ObjectMapper mapper;
    public InputHandlerImpl(String p) {
        this.path = p;
        this.mapper = new ObjectMapper();
    }

    public ObjectMapper getObjectMapper() {
        return mapper;
    }

    @Override
    public InputData getInputData() {
        if (!isProcessed) {
            throw new IllegalStateException("Input data must be processed before getting the data.");
        }
        return inputData;
    }

    @Override
    public void processInput() throws Exception{
        try {
            this.inputData = mapper.readValue(
                    new File(this.path),
                    InputData.class);
            isProcessed = true;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }
}
