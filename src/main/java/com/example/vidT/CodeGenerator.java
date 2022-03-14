package com.example.vidT;

public class CodeGenerator {
    private final int a = 100000;
    private final int b = 1000000;

    public static String generate() {
        int i = 100000 + (int) (Math.random() * 1000000);
        return "G-" + i;
    }


}