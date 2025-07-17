package com.apple.phoenix.MyCode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Versioning {

    public static void main(String[] args) {
        String s1 = "3.4.2";
        String s2 = "3.4.2.3.8.8";
        String s3 = "1.4.3";
        String s4 = "2.6.7.3.2.5";

        if(versionCmparing(s1, s2)==1)
          System.out.println("S1 is the higher version");
        else if(versionCmparing(s1, s2)==-1)
            System.out.println("S2 is the higher version");
        else
            System.out.println("Both versions are same");
    }
    private static int versionCmparing(String s1, String s2) {
        int returnVal = 0;
        s1 = s1.replace(".", "");
        s2 = s2.replace(".", "");
        int len = s1.length() <= s1.length() ? s1.length() : s2.length();
        if (s1.contains(s2) && !s1.equalsIgnoreCase(s2)) {
            returnVal = 1;
        } else if (s2.contains(s1) && !s1.equalsIgnoreCase(s2)) {
            returnVal = -1;
        } else {
            for (int i = 0; i < len; i++) {
                if (Integer.parseInt(String.valueOf(s1.charAt(i))) > Integer.parseInt(String.valueOf(s2.charAt(i)))) {
                    returnVal = 1;
                    break;
                } else if (Integer.parseInt(String.valueOf(s1.charAt(i))) < Integer.parseInt(String.valueOf(s2.charAt(i)))) {
                    returnVal = -1;
                    break;
                }
            }
        }
        return returnVal;
    }
}
