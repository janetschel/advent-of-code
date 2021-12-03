package dev.janetschel.util;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.Paths.get;

public class File {
    @SneakyThrows(value = IOException.class)
    public static List<String> read(String day) {
        return Files.lines(get("src/main/java/dev/janetschel/calendar/day%s/input.txt".formatted(day)))
                .toList();
    }

    /**
     * Reads an input.txt file based on the AoC year
     * @param day Day, formatted like: (0[1-9]|1\d|2[0-5])
     * @return Lines of file in a List
     */
    @SneakyThrows(value = IOException.class)
    public static List<String> r(String year, String day) {
        return Files.lines(get("src/main/java/dev/janetschel/aoc%s/day%s/input.txt".formatted(year, day)))
                .toList();
    }
}
