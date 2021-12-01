package dev.janetschel.calendar.day01;

import java.util.List;

import static dev.janetschel.utils.Converter.longs;
import static dev.janetschel.utils.File.read;

public class Puzzle {
    public static void main(String[] args) {
        var input = read("01");
        var result = new Puzzle().solvePart2(input);

        System.out.println("result = " + result);
    }

    public Long solve(List<String> in) {
        var input = longs(in);

        Long increased = 0L;
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i) > input.get(i - 1)) {
                increased++;
            }
        }

        return increased;
    }

    public Long solvePart2(List<String> in) {
        var input = longs(in);

        var prev = 0L;
        var increased = 0L;
        for (int i = 0; i < input.size() - 3; i++) {
            var sum = input.get(i) + input.get(i + 1) + input.get(i + 2);
            if (sum > prev) {
                increased++;
            }
            prev = sum;
        }

        return increased;
    }
}
