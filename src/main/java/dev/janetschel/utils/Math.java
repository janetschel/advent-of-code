package dev.janetschel.utils;

public class Math {
    public static Long toDecimal(String binary) {
        return Long.parseLong(binary, 2);
    }
}
