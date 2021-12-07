package dev.janetschel.calendar.year2018.day01;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.ArrayList;
import java.util.List;

public class ChronalCalibration implements GenericPuzzle {
    public Long solvePart1(List<String> in) {
        return in.stream()
                .mapToLong(Long::parseLong)
                .sum();
    }

    public Long solvePart2(List<String> in) {
        var longs = in.stream().map(Long::parseLong).toList();
        var index = 0;
        var freq = 0L;
        var seen = new ArrayList<Long>();

        while (!seen.contains(freq)) {
            seen.add(freq);
            var curr = longs.get(index++ % longs.size());
            freq += curr;
        }

        return freq;
    }
}
