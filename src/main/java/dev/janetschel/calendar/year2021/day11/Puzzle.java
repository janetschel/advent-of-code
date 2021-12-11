package dev.janetschel.calendar.year2021.day11;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.List;

import static dev.janetschel.util.datastructure.ArrayUtil.incrementArray;
import static dev.janetschel.util.datastructure.ArrayUtil.incrementDiagonally;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;
import static java.util.stream.LongStream.range;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        var octopuses = parseInput(input);

        return range(0, 100L)
                .map(i -> step(octopuses))
                .sum();
    }

    // Sadly I figured out no way to use step(...) with part 2 :( too tired to debug now
    public Long solvePart2(List<String> input) {
        var octopuses = parseInput(input);
        var flashed = 0L;

        while (true) {
            flashed++;
            incrementArray(octopuses);

            var hasFlashed = new boolean[octopuses.length][octopuses[0].length];
            var currentHasFlashed = true;

            while (currentHasFlashed) {
                currentHasFlashed = false;
                for (int i = 0; i < octopuses.length; i++) {
                    for (int j = 0; j < octopuses[i].length; j++) {
                        if (octopuses[i][j] > 9 && !hasFlashed[i][j]) {
                            hasFlashed[i][j] = true;
                            currentHasFlashed = true;

                            incrementDiagonally(i, j, octopuses);
                        }
                    }
                }
            }

            if (decrementFlashed(hasFlashed, octopuses) == 100) {
                return flashed;
            }
        }
    }

    private Long[][] parseInput(List<String> input) {
        var octopuses = new Long[input.size()][input.get(0).length()];

        for (int i = 0; i < octopuses.length; i++) {
            for (int j = 0; j < octopuses[i].length; j++) {
                octopuses[i][j] = parseLong(valueOf(input.get(i).charAt(j)));
            }
        }

        return octopuses;
    }

    private Long step(Long[][] octopuses) {
        var flashed = 0L;
        incrementArray(octopuses);

        // using boolean insteadof Boolean since it defaults to false instead of null
        var hasFlashed = new boolean[octopuses.length][octopuses[0].length];
        var currentHasFlashed = true;

        while (currentHasFlashed) {
            currentHasFlashed = false;
            for (int i = 0; i < octopuses.length; i++) {
                for (int j = 0; j < octopuses[i].length; j++) {
                    if (octopuses[i][j] > 9 && !hasFlashed[i][j]) {
                        hasFlashed[i][j] = true;
                        currentHasFlashed = true;
                        flashed++;

                        incrementDiagonally(i, j, octopuses);
                    }
                }
            }
        }

        decrementFlashed(hasFlashed, octopuses);

        return flashed;
    }

    private Long decrementFlashed(boolean[][] hasFlashed, Long[][] octopuses) {
        var count = 0L;

        for (int i = 0; i < octopuses.length; i++) {
            for (int j = 0; j < octopuses[i].length; j++) {
                if (hasFlashed[i][j]) {
                    count++;
                    octopuses[i][j] = 0L;
                }
            }
        }

        return count;
    }
}
