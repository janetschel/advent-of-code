package dev.janetschel.calendar.year2021.day08;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        var totalOfUnique = 0L;
        for (var line : input) {
            var output = line.split(" \\| ")[1];
            var digits = output.split(" ");
            var uniqueLengts = List.of(2, 3, 4, 7);

            totalOfUnique += Arrays.stream(digits)
                    .filter(digit -> uniqueLengts.contains(digit.length()))
                    .count();
        }

        return totalOfUnique;
    }

    public Long solvePart2(List<String> input) {
        var total = 0L;

        for (var line : input) {
            var out = Arrays.stream(line.split(" \\| ")[1].split(" "))
                    .toList();

            out = sortStringsInList(out);

            var mapping = figureOutWireConnection(line);
            var stringValue = out.stream()
                    .map(curr -> findInArray(mapping, curr))
                    .map(String::valueOf)
                    .reduce("", (subtotal, current) -> subtotal += current);

            total += Long.parseLong(stringValue);
        }

        return total;
    }

    private Long findInArray(String[] mapping, String curr) {
        for (var i = 0L; i < mapping.length; i++) {
            if (mapping[(int) i].equals(curr)) {
                return i;
            }
        }
        return -1L;
    }

    private String[] figureOutWireConnection(String line) {
        var digits = Arrays.stream(line.split(" \\| ")[0].split(" "))
                .toList();

        digits = sortStringsInList(digits);

        var mapping = new String[10];

        for (var digit : digits) {
            switch (digit.length()) {
                case 2 -> mapping[1] = digit;
                case 3 -> mapping[7] = digit;
                case 4 -> mapping[4] = digit;
                case 7 -> mapping[8] = digit;
            }
        }

        for (var digit : digits) {
            switch (digit.length()) {
                case 5 -> {
                    if (containsAll(digit, mapping[1])) {
                        mapping[3] = digit;
                    } else if (containsThree(digit, mapping[4])) {
                        mapping[5] = digit;
                    } else {
                        mapping[2] = digit;
                    }
                }
                case 6 -> {
                    if (containsAll(digit, mapping[4])) {
                        mapping[9] = digit;
                    } else if (containsAll(digit, mapping[1])) {
                        mapping[0] = digit;
                    } else {
                        mapping[6] = digit;
                    }
                }
            }
        }

        return mapping;
    }

    private boolean containsAll(String first, String second) {
        for (var c : second.toCharArray()) {
            if (!first.contains(String.valueOf(c))) {
                return false;
            }
        }

        return true;
    }

    private boolean containsThree(String first, String second) {
        var count = 0L;
        for (var c : second.toCharArray()) {
            if (first.contains(String.valueOf(c))) {
                count++;
            }
        }

        return count == 3;
    }

    private List<String> sortStringsInList(List<String> in) {
        var sortedNewList = new ArrayList<String>();

        for (var digit : in) {
            var list = Arrays.stream(digit.split(""))
                    .sorted()
                    .toList();

            String sorted = String.join("", list);
            sortedNewList.add(sorted);
        }

        return sortedNewList;
    }
}
