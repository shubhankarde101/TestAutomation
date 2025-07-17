package com.apple.phoenix.MyCode;

public class RoundingLogic {


    public static void main(String[] args) {

        double number = 26322.745;
        System.out.println("Double Number: " + number);
        String strResult = String.format("%.2f", number);
        int lstDigit = Integer.parseInt(String.valueOf(strResult.charAt(strResult.length() - 1)));
        String strAfterDecimalPart = "";
        if (lstDigit == 0)
            strAfterDecimalPart = strResult.split("\\.")[1].replace("0", "");
        else {
            strAfterDecimalPart = strResult.split("\\.")[1];
        }
        if (strAfterDecimalPart.length() == 2 && lstDigit >= 5 && lstDigit != 0) {
            strResult = String.format("%.1f", Double.parseDouble(strResult));
            strAfterDecimalPart = strResult.split("\\.")[1];
        }
        if (strAfterDecimalPart.length() == 1 && Integer.parseInt(String.valueOf(strAfterDecimalPart
                .charAt(strAfterDecimalPart.length() - 1))) >= 5) {
            double d1 = Math.ceil(Double.parseDouble(strResult));
            strResult = String.valueOf(d1).split("\\.")[0];
        }
        System.out.println(strResult);


    }
}
