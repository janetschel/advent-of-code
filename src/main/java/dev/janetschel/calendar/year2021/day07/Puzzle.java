package dev.janetschel.calendar.year2021.day07;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.max;


public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        var longs = Arrays.stream(input.get(0).split(","))
                .map(Long::parseLong)
                .toList();

        var smallestFuel = Long.MAX_VALUE;
        for (int i = 1; i < max(longs); i++) {
            var currentFuel = 0L;
            for (var current : longs) {
                var diff = Math.abs(current - i);
                currentFuel += diff;
            }

            if (currentFuel < smallestFuel) {
                smallestFuel = currentFuel;
            }
        }

        return smallestFuel;
    }

    public Long solvePart2(List<String> input) {
        var longs = Arrays.stream(input.get(0).split(","))
                .map(Long::parseLong)
                .toList();

        var smallestFuel = Long.MAX_VALUE;
        for (int i = 1; i < max(longs); i++) {
            var currentFuel = 0;
            for (var current : longs) {
                var diff = Math.abs(current - i);
                currentFuel += diff * (diff + 1) / 2; // gauss
            }

            if (currentFuel < smallestFuel) {
                smallestFuel = currentFuel;
            }
        }

        return smallestFuel;
    }

}
