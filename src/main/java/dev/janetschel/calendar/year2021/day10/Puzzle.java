package dev.janetschel.calendar.year2021.day10;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Puzzle implements GenericPuzzle {
    private final List<Character> openChars = List.of('(', '[', '{', '<');

    public Long solvePart1(List<String> input) {
        var corrputed = 0L;
        var incompleteChunks = new ArrayList<>();
        for (var line : input) {
            var opening = new Stack<Character>();

            for (var c : line.toCharArray()) {
                if (openChars.contains(c)) {
                    opening.push(c);
                } else if (c == ']' && opening.pop() != '[') {
                    corrputed += 57;
                } else if (c == '}' && opening.pop() != '{') {
                    corrputed += 1197;
                } else if (c == ')' && opening.pop() != '(') {
                    corrputed += 3;
                } else if (c == '>' && opening.pop() != '<') {
                    corrputed += 25137;
                }
            }
        }

        return corrputed;
    }

    public Long solvePart2(List<String> input) {
        var incomplete = input.stream()
                .map(this::calculateLine)
                .filter(line -> line >= 0)
                .sorted()
                .toList();

        return incomplete.get(incomplete.size() / 2);
    }

    private Long calculateLine(String line) {
        var opening = new Stack<Character>();
        for (var c : line.toCharArray()) {
            if (openChars.contains(c)) {
                opening.push(c);
            } else if (corresponds(c, opening)) {
                return -1L;
            }
        }

        var sum = 0L;
        while (!opening.isEmpty()) {
            sum *= 5;
            sum += openChars.indexOf(opening.pop()) + 1;
        }

        return sum;
    }

    private boolean corresponds(Character c, Stack<Character> opening) {
        return (c == ']' && opening.pop() != '[') || (c == '}' && opening.pop() != '{') ||
                (c == ')' && opening.pop() != '(') || (c == '>' && opening.pop() != '<');

    }
}
