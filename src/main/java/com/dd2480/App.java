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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dd2480.common.Parameters;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        decide();
    }

    public static void decide() {
        // Load JSON input from a file
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputData inputData = mapper.readValue(
                    new File("src/main/java/com/dd2480/input1.json"),
                    InputData.class);
            // System.out.println("NUMPOINTS: " + inputData.NUMPOINTS);
            // System.out
            // .println("First Point: " + inputData.POINTS.getPoint(0).getX() + ", "
            // + inputData.POINTS.getPoint(0).getY());
            // System.out.println("LENGTH1: " + inputData.PARAMETERS.getLENGTH1());
            // System.out.println("EPSILON: " + inputData.PARAMETERS.getEPSILON());
            // System.out.println("N_PTS: " + inputData.PARAMETERS.getNPTS());
            // System.out.println("LCM[0][1]: " + inputData.LCM.getMatrix().get(0).get(1));
            // System.out.println("PUV: " + inputData.PUV.getVector());
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

            if (launch(fuv)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // PUV puv;
        // PUMManager pumManager = new PUMManagerImpl(cmv, lcm);
        // PUM pum;
        // FUVManager fuvManager = new FUVManagerImpl(pum, puv);
        // fuvManager.computeFUV();
        // FUV fuv = fuvManager.getFUV();
        // if (launch(null)) {
        // System.out.println("True");
        // } else {
        // System.out.println("False");
        // }

    }

    private static CMV evaluateCMV(ConditionContext conditionContext) {
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
        return fuv.getVector().stream().allMatch(Boolean::booleanValue);
    }

}

class InputData {
    public int NUMPOINTS;
    public PointCollection POINTS;
    public Parameters PARAMETERS;
    public LCM LCM;
    public PUV PUV;

    InputData() {
    };
}
