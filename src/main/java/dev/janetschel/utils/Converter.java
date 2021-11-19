package dev.janetschel.utils;

import java.util.List;

public class Converter {
    public static List<Long> longs(List<String> input) {
        return input.stream()
                .map(Long::parseLong)
                .toList();
    }
}
