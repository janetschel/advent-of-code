package dev.janetschel.calendar.day06.refactored;

import dev.janetschel.util.CommandLineTools;

import java.util.List;

import static dev.janetschel.util.ArrayUtil.fromInput;
import static dev.janetschel.util.CommandLineTools.Part.FIRST;
import static dev.janetschel.util.CommandLineTools.Part.SECOND;
import static dev.janetschel.util.CommandLineTools.log;
import static dev.janetschel.util.File.read;
import static java.util.stream.LongStream.of;

public class Lanternfish {
    public static void main(String[] args) {
        var input = read("06");
        var lanternfish = new Lanternfish();

        System.out.println("Refactored solution for day 06:");
        log(new CommandLineTools.Day("06", FIRST), lanternfish::solvePart1, input);
        log(new CommandLineTools.Day("06", SECOND), lanternfish::solvePart2, input);
    }

    private Long solvePart1(List<String> input) {
        return solve(input, 80);
    }

    private Long solvePart2(List<String> input) {
        return solve(input, 256);
    }

    private Long solve(List<String> input, int repeats) {
        var counts = fromInput(input, 9);

        for (var i = 0; i < repeats; i++) {
            var newCounts = new long[9];
            System.arraycopy(counts, 1, newCounts, 0, newCounts.length - 1);

            newCounts[6] += counts[0];
            newCounts[8] += counts[0];

            counts = newCounts;
        }

        return of(counts).sum();
    }
}
