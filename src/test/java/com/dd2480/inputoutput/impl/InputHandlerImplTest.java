package com.dd2480.inputoutput.impl;

import com.dd2480.inputoutput.InputData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InputHandlerImplTest {

    private InputHandlerImpl inputHandler;

    @BeforeEach
    public void setUp() {
        String fileNameNormalfile = "test_input1.json";
        // Get the file from the resources folder
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileNameNormalfile);
        // If you need to get the full path (e.g., for processing or passing as argument)
        Path filePath = Paths.get(Main.class.getClassLoader().getResource(fileNameNormalfile).getPath());

        // Ensure the file is found in the resources folder
        assertNotNull(inputStream, "The normal input.json file should exist in the resources folder.");



    }

    @Test
    public void testProcessInput_validFile() throws IOException {
        String fileName = "test_input1.json";
        // Get the file from the resources folder
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        // If you need to get the full path (e.g., for processing or passing as argument)
        Path filePath = Paths.get(Main.class.getClassLoader().getResource(fileName).getPath());


        // Setup
        inputHandler = new InputHandlerImpl(filePath.toString());

        // Process the input file
        assertDoesNotThrow(() -> inputHandler.processInput());

        InputData inputData = inputHandler.getInputData();

        // Verify that processInput was called, and the data was processed
        assertDoesNotThrow(() -> inputHandler.getInputData(), "getInputData should not throw an exception after processing input.");
    }

    @Test
    public void testGetInputDataWithoutProcessing() {
        String fileName = "test_input1.json";
        // Get the file from the resources folder
        Path filePath = Paths.get(Main.class.getClassLoader().getResource(fileName).getPath());


        // Setup
        inputHandler = new InputHandlerImpl(filePath.toString());
        // Ensure that getInputData throws an exception if processInput hasn't been called
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> inputHandler.getInputData());
        assertEquals("Input data must be processed before getting the data.", thrown.getMessage());
    }

    @Test
    public void testProcessInput_invalidJson() throws IOException {
        // Simulate an invalid JSON parsing error

        String fileName = "invalid_input.json";
        // Try to process input and check if the exception is handled
        Path filePath = Paths.get(Main.class.getClassLoader().getResource(fileName).getPath());

        inputHandler = new InputHandlerImpl(filePath.toString());
        assertThrows(Exception.class, () -> inputHandler.processInput());
        // You might want to check how errors are logged, but for now, we only verify that no exception is thrown in the process
    }
}