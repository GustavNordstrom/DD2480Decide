package com.dd2480;

import com.dd2480.inputoutput.InputData;
import com.dd2480.inputoutput.InputHandler;
import com.dd2480.inputoutput.OutputFormatter;
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

/**
 * These tests verify the correctness of the decision-making process
 * based on various input scenarios.
 */
public class AppTest {

    /**
     * Test case with a valid input where the interceptor should be launched.
     * This test ensures that all conditions in FUV are satisfied, leading to a "YES" decision.
     */
    @Test
    public void testValidInput1_ShouldLaunch() {
        // Arrange: Load valid test input from JSON file
        InputHandler inputHandler = new InputHandlerImpl("src/test/resources/test_input1.json");
        try {
            inputHandler.processInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputData inputData = inputHandler.getInputData();

        // Act: Evaluate CMV, compute PUM and FUV
        CMV cmv =   App.evaluateCMV(new ConditionContextImpl(
                inputData.PARAMETERS, inputData.POINTS));

        PUMManagerImpl pumManager = new PUMManagerImpl(cmv, inputData.LCM);
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();

        FUVManagerImpl fuvManager = new FUVManagerImpl(pum, inputData.PUV);
        fuvManager.computeFUV();
        FUV fuv = fuvManager.getFUV();


        // Print FUV

        // Act: Check final launch decision
        boolean launchDecision = App.launch(fuv);

        // Print launchDecision
        // System.out.println("Launch Decision: " + launchDecision);

        // Assert: Verify that the interceptor should be launched
        assertTrue(launchDecision, "Expected launch decision to be YES");

        // Print results
        OutputFormatter.printLaunchDecision(launchDecision ? "YES": "NO");
        OutputFormatter.printCMV(cmv);
        OutputFormatter.printPUM(pum);
        OutputFormatter.printFUV(fuv);
    }

    /**
     * Another valid input test case where the interceptor should be launched.
     * Similar to the first test but with different input values.
     */
    @Test
    public void testValidInput2_ShouldLaunch() {
        // Arrange: Load valid test input from JSON file
        InputHandler inputHandler = new InputHandlerImpl("src/test/resources/test_input2.json");
        try {
            inputHandler.processInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputData inputData = inputHandler.getInputData();

        // Act: Evaluate CMV, compute PUM and FUV
        CMV cmv = App.evaluateCMV(new ConditionContextImpl(
                inputData.PARAMETERS, inputData.POINTS));

        PUMManagerImpl pumManager = new PUMManagerImpl(cmv, inputData.LCM);
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();

        FUVManagerImpl fuvManager = new FUVManagerImpl(pum, inputData.PUV);
        fuvManager.computeFUV();
        FUV fuv = fuvManager.getFUV();

        // Print FUV
        // System.out.println("FUV: " + fuv.getVector());

        // Act: Check final launch decision
        boolean launchDecision = App.launch(fuv);

        // Print launchDecision
        // System.out.println("Launch Decision: " + launchDecision);

        // Assert: Verify that the interceptor should be launched
        assertTrue(launchDecision, "Expected launch decision to be YES");

        // Print results
        OutputFormatter.printLaunchDecision(launchDecision ? "YES": "NO");
        OutputFormatter.printCMV(cmv);
        OutputFormatter.printPUM(pum);
        OutputFormatter.printFUV(fuv);
    }

    /**
     * Test case with valid input where the interceptor should NOT be launched.
     * This ensures that at least one condition in FUV is false, leading to a "NO" decision.
     */
    @Test
    public void testValidInput_ShouldNotLaunch() {
        // Arrange: Load valid test input where conditions prevent launch
        InputHandler inputHandler = new InputHandlerImpl("src/test/resources/test_input3.json");
        try {
            inputHandler.processInput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InputData inputData = inputHandler.getInputData();

        // Act: Evaluate CMV, compute PUM and FUV
        CMV cmv = App.evaluateCMV(new ConditionContextImpl(
                inputData.PARAMETERS, inputData.POINTS));

        PUMManagerImpl pumManager = new PUMManagerImpl(cmv, inputData.LCM);
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();

        FUVManagerImpl fuvManager = new FUVManagerImpl(pum, inputData.PUV);
        fuvManager.computeFUV();
        FUV fuv = fuvManager.getFUV();

        // Print FUV
        // System.out.println("FUV: " + fuv.getVector());

        // Act: Check final launch decision
        boolean launchDecision = App.launch(fuv);

        // Print launchDecision
        // System.out.println("Launch Decision: " + launchDecision);

        // Assert: Verify that the interceptor should NOT be launched
        assertFalse(launchDecision, "Expected launch decision to be NO");

        // Print results
        OutputFormatter.printLaunchDecision(launchDecision ? "YES": "NO");
        OutputFormatter.printCMV(cmv);
        OutputFormatter.printPUM(pum);
        OutputFormatter.printFUV(fuv);

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
