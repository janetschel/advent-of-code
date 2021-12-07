package dev.janetschel.calendar.year2018.day03;

import dev.janetschel.interfaces.GenericPuzzle;

import java.util.HashMap;
import java.util.List;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> in) {
        return calculateTakenSquares(in)
                .values()
                .stream()
                .filter(value -> value > 1)
                .count();
    }

    public String solvePart2(List<String> in) {
        var taken = calculateTakenSquares(in);

        for (var line : in) {
            var coords = line.split("@ ")[1].split(": ")[0].split(",");
            var dim = line.split(": ")[1].split("x");

            var x = Long.parseLong(coords[0]);
            var y = Long.parseLong(coords[1]);

            var width = Long.parseLong(dim[0]);
            var height = Long.parseLong(dim[1]);

            var intactClaim = true;
            for (var i = y; i < y + height; i++) {
                for (var j = x; j < x + width; j++) {
                    var key = "%s,%s".formatted(i, j);
                    if (taken.get(key) > 1) {
                        intactClaim = false;
                        break;
                    }
                }
                if (!intactClaim) {
                    break;
                }
            }

            if (intactClaim) {
                return line.split(" @")[0].substring(1);
            }
        }

        return null;
    }

    private HashMap<String, Long> calculateTakenSquares(List<String> in) {
        var taken = new HashMap<String, Long>();

        for (var line : in) {
            var coords = line.split("@ ")[1].split(": ")[0].split(",");
            var dim = line.split(": ")[1].split("x");

            var x = Long.parseLong(coords[0]);
            var y = Long.parseLong(coords[1]);

            var width = Long.parseLong(dim[0]);
            var height = Long.parseLong(dim[1]);

            for (var i = y; i < y + height; i++) {
                for (var j = x; j < x + width; j++) {
                    var key = "%s,%s".formatted(i, j);
                    if (taken.containsKey(key)) {
                        taken.put(key, taken.get(key) + 1);
                    } else {
                        taken.put(key, 1L);
                    }
                }
            }
        }

        return taken;
    }
}
