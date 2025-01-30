package com.dd2480.inputoutput;

import com.dd2480.CMV.CMV;
import com.dd2480.FUV.FUV;
import com.dd2480.PUM.PUM;
import com.dd2480.common.PUV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.dd2480.inputoutput.OutputFormatter.*;

public class OutputFormatterTest {
    CMV cmv;
    PUM pum;
    FUV fuv;
    @BeforeEach
    public void setUp() {
        pum = new PUM();
        pum.setAllTrue();
        pum.setVal(0, 1, Boolean.FALSE);
        pum.setVal(0, 3, Boolean.FALSE);
        pum.setVal(1, 0, Boolean.FALSE);
        pum.setVal(3, 0, Boolean.FALSE);

        fuv = new FUV();
        fuv.setVal(3, true);
        fuv.setVal(6, true);

        cmv = new CMV.Builder().
                setVal(0, true)
                .setVal(3, true)
                .build();
    }
    @Test
    public void testOutput() {

        String launchDecision = "YES";  // Final launch decision

        // Output with formatting
        printLaunchDecision(launchDecision);
        printCMV(cmv);
        printPUM(pum);
        printFUV(fuv);
    }
}
