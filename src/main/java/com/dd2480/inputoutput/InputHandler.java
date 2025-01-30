package com.dd2480.inputoutput;

/**
 * Process the input from
 * Provide the InputData objects
 */
public interface InputHandler {
    void processInput() throws Exception;  // To be called first
    InputData getInputData();  // Will throw exception if not called after processInput

}