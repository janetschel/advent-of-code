package dev.janetschel.calendar.year2021.day09;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.reverseOrder;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        var array = new Long[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                array[i][j] = Long.parseLong(String.valueOf(input.get(i).charAt(j)));
            }
        }

        return getLowPoints(array).stream()
                .mapToLong(num -> num + 1)
                .sum();
    }

    public Long solvePart2(List<String> input) {
        var h = input.size();
        var w = input.get(0).length();

        var grid = new Long[w][h];
        var basin = new Boolean[w][h];
        var currentY = 0;

        for (var row : input) {
            var nums = Arrays.stream(row.split(""))
                    .map(String::valueOf)
                    .toList();

            for (int x = 0; x < nums.size(); x++) {
                grid[x][currentY] = Long.parseLong(nums.get(x));
                basin[x][currentY] = false;
            }

            currentY++;
        }

        var basinCounts = new ArrayList<Long>();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if ((x <= 0 || grid[x][y] < grid[x - 1][y]) && (x >= w - 1 || grid[x][y] < grid[x + 1][y]) &&
                        (y <= 0 || grid[x][y] < grid[x][y - 1]) && (y >= h - 1 || grid[x][y] < grid[x][y + 1])) {

                    basinCounts.add(getCount(x, y, w, h, grid, basin));
                }
            }
        }

        return basinCounts.stream()
                .sorted(reverseOrder())
                .limit(3)
                .reduce(1L, (total, current) -> total *= current);
    }

    Long getCount(int x, int y, int w, int h, Long[][] grid, Boolean[][] basin) {
        if (basin[x][y] || grid[x][y] == 9) {
            return 0L; // we don't care about the 9
        }

        var count = 1L;
        basin[x][y] = true;

        if (x > 0) {
            count += getCount(x - 1, y, w, h, grid, basin);
        }

        if (y > 0) {
            count += getCount(x, y - 1, w, h, grid, basin);
        }

        if (x < w - 1) {
            count += getCount(x + 1, y, w, h, grid, basin);
        }

        if (y < h - 1) {
            count += getCount(x, y + 1, w, h, grid, basin);
        }

        return count;
    }

    // Don't look at this... too lazy to refactor today -> runtime is OK ;) (~14ms)
    private List<Long> getLowPoints(Long[][] array) {
        var lows = new ArrayList<Long>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == 0 && j == 0) {
                    if (array[i][j] < array[i + 1][j] && array[i][j] < array[i][j + 1]) {
                        lows.add(array[i][j]);
                    }
                } else if (i == 0 && j == array[i].length - 1) {
                    if (array[i][j] < array[i + 1][j] && array[i][j] < array[i][j - 1]) {
                        lows.add(array[i][j]);
                    }
                } else if (i == 0) {
                    if (array[i][j] < array[i + 1][j] && array[i][j] < array[i][j - 1] && array[i][j] < array[i][j + 1]) {
                        lows.add(array[i][j]);
                    }
                } else if (j == 0 && i == array.length - 1) {
                    if (array[i][j] < array[i - 1][j] && array[i][j] < array[i][j + 1]) {
                        lows.add(array[i][j]);
                    }
                } else if (j == array[i].length - 1 && i == array.length - 1) {
                    if (array[i][j] < array[i - 1][j] && array[i][j] < array[i][j - 1]) {
                        lows.add(array[i][j]);
                    }
                } else if (i == array.length - 1) {
                    if (array[i][j] < array[i - 1][j] && array[i][j] < array[i][j - 1] && array[i][j] < array[i][j + 1]) {
                        lows.add(array[i][j]);
                    }
                } else if (j == 0) {
                    if (array[i][j] < array[i - 1][j] && array[i][j] < array[i + 1][j] && array[i][j] < array[i][j + 1]) {
                        lows.add(array[i][j]);
                    }
                } else if (j == array[i].length - 1) {
                    if (array[i][j] < array[i - 1][j] && array[i][j] < array[i + 1][j] && array[i][j] < array[i][j - 1]) {
                        lows.add(array[i][j]);
                    }
                } else {
                    if (array[i][j] < array[i - 1][j] && array[i][j] < array[i + 1][j] && array[i][j] < array[i][j - 1] && array[i][j] < array[i][j + 1]) {
                        lows.add(array[i][j]);
                    }
                }
            }
        }

        return lows;
    }
}
