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
import com.dd2480.inputoutput.OutputFormatter;
import com.dd2480.inputoutput.OutputHandler;
import com.dd2480.inputoutput.impl.InputHandlerImpl;
import com.dd2480.inputoutput.impl.OutputHandlerImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dd2480.common.Parameters;

/**
 * Hello world!
 *
 */
public class App {
    /*
     * Usage lin/mac: mvn exec:java -Dexec.args="src/test/resources/test_input1.json"
     * usage windows: mvn exec:java -D"exec.args"="src\test\resources\test_input1.json"
     * 
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage linux/mac: mvn exec:java -Dexec.args=\"path/to/input.json\", usage windows: mvn exec:java -D\"exec.args\"=\"path\\to\\input.json\""); 
            return;
        }

        String jsonFilePath = args[0]; // Get JSON file from command-line arguments
        decide(jsonFilePath);
    }

    public static void decide(String jsonFilePath) {
        // Load JSON input from a file
        InputHandler inputHandler = new InputHandlerImpl(jsonFilePath);

        try {
            inputHandler.processInput();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        InputData inputData = inputHandler.getInputData();
        Parameters parameters = inputData.PARAMETERS;
        PointCollection pointCollection = inputData.POINTS;
        LCM lcm = inputData.LCM;
        PUV puv = inputData.PUV;

        ConditionContext conditionContext = new ConditionContextImpl(parameters, pointCollection);
        CMV cmv = evaluateCMV(conditionContext);

        PUMManager pumManager = new PUMManagerImpl(cmv, lcm);
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();

        FUVManager fuvManager = new FUVManagerImpl(pum, puv);
        fuvManager.computeFUV();
        FUV fuv = fuvManager.getFUV();

        OutputFormatter.printLaunchDecision(launch(fuv) ? "YES": "NO");
        OutputFormatter.printCMV(cmv);
        OutputFormatter.printPUM(pum);
        OutputFormatter.printFUV(fuv);

    }

    public static CMV evaluateCMV(ConditionContext conditionContext) {
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

        ConditionManager conditionManager = new ConditionManagerImpl(conditions);

        conditionManager.evaluateAll(conditionContext);

        return ((ConditionManagerImpl) conditionManager).getCMV();
    }

    public static boolean launch(FUV fuv) {
        // OutputHandler outputHandler = new OutputHandlerImpl();
        // outputHandler.print(fuv);
        return fuv.getVector().stream().allMatch(Boolean::booleanValue);
    }

}
