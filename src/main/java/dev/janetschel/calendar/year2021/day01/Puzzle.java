package dev.janetschel.calendar.year2021.day01;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.List;

import static dev.janetschel.util.converter.Converter.longs;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        var in = longs(input);
        var increased = 0L;
        for (int i = 1; i < in.size(); i++) {
            if (in.get(i) > in.get(i - 1)) {
                increased++;
            }
        }

        return increased;
    }

    public Long solvePart2(List<String> input) {
        var in = longs(input);
        var prev = 0L;
        var increased = 0L;
        for (int i = 0; i < in.size() - 3; i++) {
            var sum = in.get(i) + in.get(i + 1) + in.get(i + 2);
            if (sum > prev) {
                increased++;
            }
            prev = sum;
        }

        return increased;
    }
}
