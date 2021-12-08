package dev.janetschel.calendar.year2020.day25;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.List;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> in) {
        var pkCard = Long.parseLong(in.get(0));
        var pkDoor = Long.parseLong(in.get(1));

        var loopSizeCard = findLoopSize(pkCard);
        var loopSizeDoor = findLoopSize(pkDoor);

        var value = 1L;
        for (var i = 0; i < loopSizeCard; i++) {
            value *= pkDoor;
            value %= 20201227L;
        }

        return value;
    }

    public Object solvePart2(List<String> in) {
        return null; // there is no part 2 in AoC 2020
    }

    private Long findLoopSize(Long pkCard) {
        var value = 1L;
        var subjectNumber = 7L;
        var loopSize = 0L;

        while (true) {
            value *= subjectNumber;
            value %= 20201227L;

            loopSize++;
            if (value == pkCard) {
                return loopSize;
            }
        }
    }
}
