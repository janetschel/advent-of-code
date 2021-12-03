package dev.janetschel.calendar.day02;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.List;

import static dev.janetschel.util.File.read;

public class Puzzle implements GenericPuzzle {
    public static void main(String[] args) {
        var input = read("02");
        var p = new Puzzle();

        var result = p.solve(input);
        var resultPart2 = p.solvePart2(input);

        System.out.println("result = " + result);
        System.out.println("resultPart2 = " + resultPart2);
    }

    public Long solve(List<String> input) {
        var hor = 0L;
        var depth = 0L;

        for (var cmd: input) {
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

        for (var cmd: input) {
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
