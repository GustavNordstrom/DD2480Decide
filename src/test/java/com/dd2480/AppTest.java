package com.dd2480;

import com.dd2480.inputoutput.InputData;
import com.dd2480.inputoutput.InputHandler;
import com.dd2480.inputoutput.impl.InputHandlerImpl;
import com.dd2480.common.Parameters;
import com.dd2480.common.PointCollection;
import com.dd2480.common.LCM;
import com.dd2480.common.PUV;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.impl.ConditionContextImpl;
import com.dd2480.CMV.CMV;
import com.dd2480.PUM.PUM;
import com.dd2480.PUM.PUMManager;
import com.dd2480.PUM.impl.PUMManagerImpl;
import com.dd2480.FUV.FUV;
import com.dd2480.FUV.FUVManager;
import com.dd2480.FUV.impl.FUVManagerImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class AppTest {

    @Test
    public void testValidInput1_ShouldLaunch() {
        // Arrange
        InputHandler inputHandler = new InputHandlerImpl("src/test/resources/test_input1.json");
        try {
            inputHandler.processInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputData inputData = inputHandler.getInputData();

        // Act
        PUMManagerImpl pumManager = new PUMManagerImpl(
                App.evaluateCMV(new ConditionContextImpl(
                        inputData.PARAMETERS, inputData.POINTS)),
                inputData.LCM);
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();

        FUVManagerImpl fuvManager = new FUVManagerImpl(pum, inputData.PUV);
        fuvManager.computeFUV();
        FUV fuv = fuvManager.getFUV();

        // Print FUV
        // System.out.println("FUV: " + fuv.getVector());

        // Act
        boolean launchDecision = App.launch(fuv);

        // Print launchDecision
        // System.out.println("Launch Decision: " + launchDecision);

        // Assert
        assertTrue(launchDecision, "Expected launch decision to be YES");
    }

    @Test
    public void testValidInput2_ShouldLaunch() {
        // Arrange
        InputHandler inputHandler = new InputHandlerImpl("src/test/resources/test_input2.json");
        try {
            inputHandler.processInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputData inputData = inputHandler.getInputData();

        // Act
        PUMManagerImpl pumManager = new PUMManagerImpl(
                App.evaluateCMV(new ConditionContextImpl(
                        inputData.PARAMETERS, inputData.POINTS)),
                inputData.LCM);
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();

        FUVManagerImpl fuvManager = new FUVManagerImpl(pum, inputData.PUV);
        fuvManager.computeFUV();
        FUV fuv = fuvManager.getFUV();

        // Print FUV
        // System.out.println("FUV: " + fuv.getVector());

        // Act
        boolean launchDecision = App.launch(fuv);

        // Print launchDecision
        // System.out.println("Launch Decision: " + launchDecision);

        // Assert
        assertTrue(launchDecision, "Expected launch decision to be YES");
    }

    @Test
    public void testValidInput_ShouldNotLaunch() {
        // Arrange
        InputHandler inputHandler = new InputHandlerImpl("src/test/resources/test_input3.json");
        try {
            inputHandler.processInput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InputData inputData = inputHandler.getInputData();

        // Act
        PUMManagerImpl pumManager = new PUMManagerImpl(
                App.evaluateCMV(new ConditionContextImpl(
                        inputData.PARAMETERS, inputData.POINTS)),
                inputData.LCM);
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();

        FUVManagerImpl fuvManager = new FUVManagerImpl(pum, inputData.PUV);
        fuvManager.computeFUV();
        FUV fuv = fuvManager.getFUV();

        // Print FUV
        // System.out.println("FUV: " + fuv.getVector());

        // Act
        boolean launchDecision = App.launch(fuv);

        // Print launchDecision
        // System.out.println("Launch Decision: " + launchDecision);

        // Assert
        assertFalse(launchDecision, "Expected launch decision to be NO");
    }

    // @Test
    // public void testInvalidInput_ShouldThrowException() {
    // // Arrange
    // InputHandler inputHandler = new
    // InputHandlerImpl("src/test/resources/invalid_test.json");

    // // Act & Assert
    // assertThrows(RuntimeException.class, () -> {
    // inputHandler.processInput();
    // }, "Expected an exception due to invalid input");
    // }

}
