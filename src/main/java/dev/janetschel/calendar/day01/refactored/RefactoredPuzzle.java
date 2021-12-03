package dev.janetschel.calendar.day01.refactored;

import java.util.List;

import static dev.janetschel.util.Converter.*;
import static dev.janetschel.util.File.read;

public class RefactoredPuzzle {
    public static void main(String[] args) {
        var input = longs(read("01"));
        var rp = new RefactoredPuzzle();

        System.out.printf("Solution Part 1: %s%n", rp.solvePart1(input));
        System.out.printf("Solution Part 2: %s%n", rp.solvePart2(input));
    }

    public Long solvePart1(List<Long> in) {
        return pairs(in).stream()
                .filter(p -> p.first() < p.second())
                .count();
    }

    public Long solvePart2(List<Long> in) {
        return solvePart1(combine(in, 3));
    }
}
