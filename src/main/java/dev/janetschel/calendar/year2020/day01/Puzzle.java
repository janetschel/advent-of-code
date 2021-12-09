package dev.janetschel.calendar.year2020.day01;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.HashSet;
import java.util.List;

import static dev.janetschel.util.converter.Converter.longs;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> in) {
        var diffs = new HashSet<Long>();

        for (var num : longs(in)) {
            var diff = 2020 - num;

            if (diffs.contains(diff)) {
                return diff * num;
            }

            diffs.add(num);
        }

        return -1L;
    }

    public Long solvePart2(List<String> in) {
        var input = longs(in);

        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                for (int k = j + 1; k < input.size(); k++) {
                    var sum = input.get(i) + input.get(j) + input.get(k);

                    if (sum == 2020) {
                        return input.get(i) * input.get(j) * input.get(k);
                    }
                }
            }
        }

        return -1L;
    }
}
