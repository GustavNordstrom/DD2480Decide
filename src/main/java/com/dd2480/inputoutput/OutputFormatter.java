package com.dd2480.inputoutput;

import com.dd2480.CMV.CMV;
import com.dd2480.FUV.FUV;
import com.dd2480.PUM.PUM;

/**
 * Format the object
 * Output objects in a pretty way
 */
public class OutputFormatter {

    // Print Final Launch Decision
    public static void printLaunchDecision(String launchDecision) {
        System.out.printf("LAUNCH Decision: %-4s\n\n", launchDecision);  // Left-aligned output
    }

    // Print Conditions Met Vector
    public static void printCMV(CMV cmv) {

        System.out.println("CMV - Conditions Met Vector:");
        System.out.println("----------------------------");
        System.out.print("[ ");
        for (int i = 0; i < cmv.getVector().size(); i++) {
            System.out.printf(cmv.getVector().get(i) ? "true": "false");
            if (i < cmv.getVector().size() - 1) System.out.print(", ");  // Add comma for separation
        }
        System.out.println(" ]\n");
    }

    // Print Preliminary Unlocking Matrix
    public static void printPUM(PUM pum) {
        System.out.println("PUM - Preliminary Unlocking Matrix:");
        System.out.println("------------------------------------");
        for (int i = 0; i < pum.getRowNumber(); i++) {
            System.out.print("[ ");
            for (int j = 0; j < pum.getColNumber(); j++) {
                System.out.printf(pum.getMatrix().get(i).get(j) ? "true": "false");  // Format numbers to 2 decimal places
                if (j < pum.getColNumber() - 1) System.out.print(", ");  // Add comma for separation
            }
            System.out.println(" ]");
        }
        System.out.println();
    }

    // Print Final Unlocking Vector
    public static void printFUV(FUV fuv) {
        System.out.println("FUV - Final Unlocking Vector:");
        System.out.println("----------------------------");
        System.out.print("[ ");
        for (int i = 0; i < fuv.getVector().size(); i++) {
            System.out.printf(fuv.getVector().get(i) ? "true": "false");  // Format numbers to 2 decimal places
            if (i < fuv.getVector().size() - 1) System.out.print(", ");  // Add comma for separation
        }
        System.out.println(" ]\n");
    }
}