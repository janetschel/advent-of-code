package dev.janetschel.calendar.year2021.day06;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import static dev.janetschel.util.converter.Converter.longs;
import static java.lang.Integer.parseInt;

public class Puzzle implements GenericPuzzle {
    // Could've refactored like in part2 to make it run faster (pt1: ~86ms, pt2: ~4ms) but nah
    public Long solvePart1(List<String> input) {
        var longs = longs(Arrays.stream(input.get(0).split(",")).toList());
        var current = new ArrayList<>(longs);

        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < current.size(); j++) {
                if (current.get(j) == 0) {
                    current.set(j, 6L);
                    current.add(9L);
                } else {
                    current.set(j, current.get(j) - 1);
                }
            }

        }

        return (long) current.size();
    }

    public Long solvePart2(List<String> input) {
        var counts = new long[9];

        for (var line : input.get(0).split(",")) {
            counts[parseInt(line)]++;
        }

        for (var i = 0; i < 256; i++) {
            var newCounts = new long[9];

            //noinspection ManualArrayCopy
            for (int j = 1; j < newCounts.length; j++) {
                newCounts[j - 1] = counts[j];
            }

            newCounts[6] += counts[0];
            newCounts[8] += counts[0];

            counts = newCounts;
        }

        return LongStream.of(counts).

                sum();
    }
}
