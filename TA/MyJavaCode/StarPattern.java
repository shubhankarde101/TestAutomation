package com.apple.phoenix.MyCode;

public class StarPattern {

    public static void main(String[] args) {
        int rows = 5;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= (rows - i + 1); j++) {
                System.out.print(" ");
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

