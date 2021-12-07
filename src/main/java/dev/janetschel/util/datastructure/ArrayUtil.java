package dev.janetschel.util.datastructure;

import dev.janetschel.calendar.year2021.day04.models.Number;

import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ArrayUtil {
    public static Number[][] rotateClockwise(Number[][] a) {
        Number[][] ret = new Number[a.length][a[0].length];

        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a[0].length; c++) {
                ret[c][a.length - 1 - r] = a[r][c];
            }
        }

        return ret;
    }

    public static Long[][] initSquareArray(int size) {
        var array = new Long[size][size];
        for (Long[] longs : array) {
            Arrays.fill(longs, 0L);
        }

        return array;
    }

    public static long[] fromInput(List<String> input, int delta) {
        var counts = new long[delta];

        for (var line : input.get(0).split(",")) {
            counts[parseInt(line)]++;
        }

        return counts;
    }
}
