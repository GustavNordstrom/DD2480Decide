package com.dd2480;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dd2480.CMV.CMV;
import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.ConditionManager;
import com.dd2480.CMV.impl.ConditionContextImpl;
import com.dd2480.CMV.impl.ConditionEight;
import com.dd2480.CMV.impl.ConditionEleven;
import com.dd2480.CMV.impl.ConditionFive;
import com.dd2480.CMV.impl.ConditionFour;
import com.dd2480.CMV.impl.ConditionFourteen;
import com.dd2480.CMV.impl.ConditionManagerImpl;
import com.dd2480.CMV.impl.ConditionNine;
import com.dd2480.CMV.impl.ConditionOne;
import com.dd2480.CMV.impl.ConditionSeven;
import com.dd2480.CMV.impl.ConditionSix;
import com.dd2480.CMV.impl.ConditionTen;
import com.dd2480.CMV.impl.ConditionThirteen;
import com.dd2480.CMV.impl.ConditionThree;
import com.dd2480.CMV.impl.ConditionTwelve;
import com.dd2480.CMV.impl.ConditionTwo;
import com.dd2480.CMV.impl.ConditionZero;
import com.dd2480.FUV.FUV;
import com.dd2480.FUV.FUVManager;
import com.dd2480.FUV.impl.FUVManagerImpl;
import com.dd2480.PUM.PUM;
import com.dd2480.PUM.PUMManager;
import com.dd2480.PUM.impl.PUMManagerImpl;
import com.dd2480.common.LCM;
import com.dd2480.common.PUV;
import com.dd2480.common.PointCollection;
import com.dd2480.inputoutput.InputData;
import com.dd2480.inputoutput.InputHandler;
import com.dd2480.inputoutput.OutputHandler;
import com.dd2480.inputoutput.impl.InputHandlerImpl;
import com.dd2480.inputoutput.impl.OutputHandlerImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dd2480.common.Parameters;

/**
 * Main application class for the interceptor launch decision system.
 * This program processes radar tracking data from a JSON file and evaluates
 * whether an interceptor should be launched based on predefined conditions.
 */
public class App {
    /*
     * Usage lin/mac: mvn exec:java
     * -Dexec.args="src/test/resources/test_input1.json"
     * usage windows: mvn exec:java
     * -D"exec.args"="src\test\resources\test_input1.json"
     * 
     */
    public static void main(String[] args) {
        // if (args.length < 1) {
        // // System.err.println("Usage linux/mac: mvn exec:java
        // //
        // -Dexec.args=\"/Users/zuoxu/Desktop/DD2480Decide/src/main/java/com/dd2480/input1.json",
        // // usage windows: mvn exec:java -D\"exec.args\"=\"path\\to\\input.json\"");
        // return;
        // }

        // String jsonFilePath = args[0]; // Get JSON file from command-line arguments

        // The file path is hard coded for test, please change it if necessary!
        decide("src/main/java/com/dd2480/input1.json");
    }

    /**
     * Processes the decision-making workflow based on the input JSON file.
     *
     * @param jsonFilePath The path to the input JSON file.
     */
    public static void decide(String jsonFilePath) {
        // Load JSON input from a file
        InputHandler inputHandler = new InputHandlerImpl(jsonFilePath);

        try {
            inputHandler.processInput();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Extract input data
        InputData inputData = inputHandler.getInputData();
        Parameters parameters = inputData.PARAMETERS;
        PointCollection pointCollection = inputData.POINTS;
        LCM lcm = inputData.LCM;
        PUV puv = inputData.PUV;

        // Evaluate conditions and generate CMV
        ConditionContext conditionContext = new ConditionContextImpl(parameters, pointCollection);
        CMV cmv = evaluateCMV(conditionContext);

        // Compute the Preliminary Unlocking Matrix (PUM)
        PUMManager pumManager = new PUMManagerImpl(cmv, lcm);
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();

        // Compute the Final Unlocking Vector (FUV)
        FUVManager fuvManager = new FUVManagerImpl(pum, puv);
        fuvManager.computeFUV();
        FUV fuv = fuvManager.getFUV();

        // Generate output decision
        OutputHandler outputHandler = new OutputHandlerImpl();

        // Print final decision
        if (launch(fuv)) {
            outputHandler.print("YES");
        } else {
            outputHandler.print("NO");
        }

        // Print intermediate results (CMV, PUM, FUV)
        outputHandler.print(cmv)
                .print(pum)
                .print(fuv);

    }

    /**
     * Evaluates all launch conditions and generates the Condition Met Vector (CMV).
     *
     * @param conditionContext The context containing parameters and points.
     * @return CMV containing the results of each condition evaluation.
     */
    public static CMV evaluateCMV(ConditionContext conditionContext) {
        // Initialize and register all conditions
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new ConditionZero());
        conditions.add(new ConditionOne());
        conditions.add(new ConditionTwo());
        conditions.add(new ConditionThree());
        conditions.add(new ConditionFour());
        conditions.add(new ConditionFive());
        conditions.add(new ConditionSix());
        conditions.add(new ConditionSeven());
        conditions.add(new ConditionEight());
        conditions.add(new ConditionNine());
        conditions.add(new ConditionTen());
        conditions.add(new ConditionEleven());
        conditions.add(new ConditionTwelve());
        conditions.add(new ConditionThirteen());
        conditions.add(new ConditionFourteen());

        // Evaluate all conditions
        ConditionManager conditionManager = new ConditionManagerImpl(conditions);
        conditionManager.evaluateAll(conditionContext);

        return ((ConditionManagerImpl) conditionManager).getCMV();
    }

    /**
     * Determines whether to launch the interceptor based on the Final Unlocking
     * Vector (FUV).
     * The interceptor is launched only if all elements in FUV are true.
     *
     * @param fuv The Final Unlocking Vector.
     * @return true if launch conditions are met, false otherwise.
     */
    public static boolean launch(FUV fuv) {
        // OutputHandler outputHandler = new OutputHandlerImpl();
        // outputHandler.print(fuv);
        return fuv.getVector().stream().allMatch(Boolean::booleanValue);
    }

}
