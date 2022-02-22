package com.example.vidT;

public class CodeGenerator {
    private int a = 100000;
    private int b = 1000000;

    public static String generate() {
        int i = 100000 + (int) (Math.random() * 1000000);
        String code = "G-" + i;
        return code;
    }


}