package com.dd2480;

import com.dd2480.FUV.FUV;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        decide();
    }

    public static void decide() {
        System.out.println("True");

    }

    public static boolean launch(FUV fuv) {
        return fuv.getVector().stream().allMatch(Boolean::booleanValue);
    }

}
