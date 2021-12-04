package dev.janetschel.util;

import dev.janetschel.calendar.day04.models.Number;

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
}
