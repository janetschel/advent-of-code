package dev.janetschel;

import dev.janetschel.annotation.ExecutedDays;
import dev.janetschel.interfaces.GenericPuzzle;
import dev.janetschel.util.meta.CommandLineTools;
import lombok.SneakyThrows;

import java.io.IOException;

import static com.google.common.reflect.ClassPath.from;
import static dev.janetschel.annotation.ExecutedDays.Day.DAY_09;
import static dev.janetschel.annotation.ExecutedDays.Day.getDayRepresentation;
import static dev.janetschel.util.meta.CommandLineTools.Part.FIRST;
import static dev.janetschel.util.meta.CommandLineTools.Part.SECOND;
import static dev.janetschel.util.meta.CommandLineTools.log;
import static dev.janetschel.util.meta.File.read;
import static java.lang.Thread.currentThread;

@ExecutedDays(day = DAY_09)
public class Calendar {
    private static final String CURRENT_YEAR = "2021";

    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    @SneakyThrows(value = IOException.class)
    public static void main(String[] args) {
        System.out.printf("Solutions for Advent of Code %s", CURRENT_YEAR);
        var specificDay = parseAnnotation();

        from(currentThread().getContextClassLoader())
                .getTopLevelClasses()
                .stream()
                .filter(c -> c.getName().startsWith("dev.janetschel.calendar.year%s".formatted(CURRENT_YEAR)) && !c.getName().contains("refactored") && !c.getName().contains("models"))
                .filter(c -> "00".equals(specificDay) || c.getName().contains(specificDay))
                .forEach(clazz -> new InvokablePuzzle((Class<? extends GenericPuzzle>) clazz.load()).invoke());
    }

    public record InvokablePuzzle(Class<? extends GenericPuzzle> clazz) {
        @SneakyThrows
        void invoke() {
            var puzzle = clazz.getDeclaredConstructor().newInstance();
            var name = puzzle.getClass().getPackageName();
            var day = name.substring(name.length() - 2);

            System.out.printf("\nDay %s:\n", day);

            var input = read(CURRENT_YEAR, day);

            log(new CommandLineTools.Day(day, FIRST), puzzle::solvePart1, input);
            log(new CommandLineTools.Day(day, SECOND), puzzle::solvePart2, input);
        }
    }

    private static String parseAnnotation() {
        var annotation = Calendar.class.getAnnotation(ExecutedDays.class);
        return getDayRepresentation(annotation.day());
    }
}
