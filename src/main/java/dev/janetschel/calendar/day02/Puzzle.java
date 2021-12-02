package dev.janetschel.calendar.day02;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.List;

import static dev.janetschel.utils.File.read;

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

        for (var dir: input) {
            var in = dir.split(" ");

            switch (in[0]) {
                case "forward" -> hor += Long.parseLong(in[1]);
                case "down" -> depth += Long.parseLong(in[1]);
                case "up" -> {
                    depth -= Long.parseLong(in[1]);
                }
            }
        }

        return hor * depth;
    }

    public Long solvePart2(List<String> input) {
        var hor = 0L;
        var depth = 0L;
        var aim = 0L;

        for (var dir: input) {
            var in = dir.split(" ");

            switch (in[0]) {
                case "forward" -> {
                    hor += Long.parseLong(in[1]);
                    depth += aim * Long.parseLong(in[1]);
                }
                case "down" -> aim += Long.parseLong(in[1]);
                case "up" -> {
                    aim -= Long.parseLong(in[1]);
                }
            }
        }

        return hor * depth;
    }
}
