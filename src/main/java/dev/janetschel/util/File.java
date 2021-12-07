package dev.janetschel.util;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.Paths.get;

public class File {
    @SneakyThrows(value = IOException.class)
    public static List<String> read(String year, String day) {
        return Files.lines(get("src/main/java/dev/janetschel/calendar/year%s/day%s/input.txt".formatted(year, day)))
                .toList();
    }
}
