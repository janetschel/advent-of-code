package dev.janetschel;

import dev.janetschel.annotation.ExecutedDays;
import dev.janetschel.interfaces.GenericPuzzle;
import dev.janetschel.util.CommandLineTools;
import lombok.SneakyThrows;

import java.io.IOException;

import static com.google.common.reflect.ClassPath.from;
import static dev.janetschel.annotation.ExecutedDays.Day.ALL_DAYS;
import static dev.janetschel.annotation.ExecutedDays.Day.getDayRepresentation;
import static dev.janetschel.util.CommandLineTools.Part.FIRST;
import static dev.janetschel.util.CommandLineTools.Part.SECOND;
import static dev.janetschel.util.CommandLineTools.log;
import static dev.janetschel.util.File.read;
import static java.lang.Thread.currentThread;

@ExecutedDays(day = ALL_DAYS)
public class Calendar {
    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    @SneakyThrows(value = IOException.class)
    public static void main(String[] args) {
        System.out.print("Solutions for Advent of Code 2021");
        var specificDay = parseAnnotation();

        from(currentThread().getContextClassLoader())
                .getTopLevelClasses()
                .stream()
                .filter(c -> c.getName().startsWith("dev.janetschel.calendar") && !c.getName().contains("refactored") && !c.getName().contains("models"))
                .filter(c -> "00".equals(specificDay) || c.getName().contains(specificDay))
                .forEach(clazz -> new InvokablePuzzle((Class<? extends GenericPuzzle>) clazz.load()).invoke());
    }

    public record InvokablePuzzle(Class<? extends GenericPuzzle> clazz) {
        @SneakyThrows
        void invoke() {
            var puzzle = clazz.getDeclaredConstructor().newInstance();
            var name = puzzle.getClass().getPackageName();
            var day = name.substring(name.length() - 2);

            var input = read(day);

            System.out.printf("\nDay %s:\n", day);
            log(new CommandLineTools.Day(day, FIRST), puzzle::solvePart1, input);
            log(new CommandLineTools.Day(day, SECOND), puzzle::solvePart2, input);
        }
    }

    private static String parseAnnotation() {
        var annotation = Calendar.class.getAnnotation(ExecutedDays.class);
        return getDayRepresentation(annotation.day());
    }
}
