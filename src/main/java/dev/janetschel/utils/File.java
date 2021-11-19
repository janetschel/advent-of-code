package dev.janetschel.utils;

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
}
