package dev.janetschel.calendar.year2021.day08;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.List;

import static dev.janetschel.util.datastructure.ArrayUtil.findInArray;
import static dev.janetschel.util.datastructure.StringUtils.*;
import static java.lang.Long.parseLong;
import static java.util.Arrays.asList;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        return input.stream()
                .flatMap(line -> getValues(line, 1).stream())
                .filter(digit -> List.of(2, 3, 4, 7).contains(digit.length()))
                .count();
    }

    public Long solvePart2(List<String> input) {
        return input.stream()
                .mapToLong(line -> parseLong(calculateOutputValue(line)))
                .sum();
    }

    private String calculateOutputValue(String line) {
        var connections = figureOutWireConnection(line);

        return getValues(line, 1).stream()
                .map(curr -> findInArray(connections, curr))
                .map(String::valueOf)
                .reduce("", (subtotal, current) -> subtotal += current);
    }

    private String[] figureOutWireConnection(String line) {
        var inputDigits = getValues(line, 0);
        var connections = new String[10];

        inputDigits.forEach(digit -> {
            switch (digit.length()) {
                case 2 -> connections[1] = digit;
                case 3 -> connections[7] = digit;
                case 4 -> connections[4] = digit;
                case 7 -> connections[8] = digit;
            }
        });

        inputDigits.forEach(digit -> {
            if (digit.length() == 5) {
                if (containsAll(digit, connections[1])) {
                    connections[3] = digit;
                } else if (containsTimes(digit, connections[4], 3)) {
                    connections[5] = digit;
                } else {
                    connections[2] = digit;
                }
            } else if (digit.length() == 6) {
                if (containsAll(digit, connections[4])) {
                    connections[9] = digit;
                } else if (containsAll(digit, connections[1])) {
                    connections[0] = digit;
                } else {
                    connections[6] = digit;
                }
            }
        });

        return connections;
    }

    private List<String> getValues(String line, int index) {
        return sortStringsInList(asList(line.split(" \\| ")[index].split(" ")));
    }
}
