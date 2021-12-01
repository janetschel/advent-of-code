package dev.janetschel.calendar.day01;

import java.util.List;

import static dev.janetschel.utils.Converter.longs;
import static dev.janetschel.utils.File.read;

public class Puzzle {
    public static void main(String[] args) {
        var input = longs(read("01"));
        var p = new Puzzle();
        
        var result = p.solve(input);
        var resultPt2 = p.solvePart2(input);

        System.out.println("result = " + result);
        System.out.println("resultPt2 = " + resultPt2);
    }

    public Long solve(List<Long> in) {
        var increased = 0L;
        for (int i = 1; i < in.size(); i++) {
            if (in.get(i) > in.get(i - 1)) {
                increased++;
            }
        }

        return increased;
    }

    public Long solvePart2(List<Long> in) {
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
