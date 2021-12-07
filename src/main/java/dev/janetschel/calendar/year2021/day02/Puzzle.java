package dev.janetschel.calendar.year2021.day02;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.List;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        var hor = 0L;
        var depth = 0L;

        for (var cmd : input) {
            var in = cmd.split(" ");
            var dir = in[0];
            var x = Long.parseLong(in[1]);

            switch (dir) {
                case "forward" -> hor += x;
                case "down" -> depth += x;
                case "up" -> depth -= x;
            }
        }

        return hor * depth;
    }

    public Long solvePart2(List<String> input) {
        var hor = 0L;
        var depth = 0L;
        var aim = 0L;

        for (var cmd : input) {
            var in = cmd.split(" ");
            var dir = in[0];
            var x = Long.parseLong(in[1]);

            switch (dir) {
                case "forward" -> {
                    hor += x;
                    depth += aim * x;
                }
                case "down" -> aim += x;
                case "up" -> aim -= x;
            }
        }

        return hor * depth;
    }
}
