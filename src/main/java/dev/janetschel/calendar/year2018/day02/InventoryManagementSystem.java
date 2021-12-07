package dev.janetschel.calendar.year2018.day02;

import com.google.common.base.CharMatcher;
import dev.janetschel.interfaces.GenericPuzzle;

import java.util.List;
import java.util.stream.Stream;

import static dev.janetschel.util.math.Bool.toInt;

public class InventoryManagementSystem implements GenericPuzzle {
    public Long solvePart1(List<String> in) {
        var counts = new int[2];

        for (var line : in) {
            counts[0] += containsChar(line, 2);
            counts[1] += containsChar(line, 3);
        }

        return (long) counts[0] * counts[1];
    }

    public Object solvePart2(List<String> in) {
        for (int i = 0; i < in.size(); i++) {
            var first = in.get(i).toCharArray();
            for (int j = i + 1; j < in.size(); j++) {
                var second = in.get(j).toCharArray();
                var clashes = 0;

                for (int k = 0; k < first.length; k++) {
                    if (first[k] != second[k] && ++clashes > 1) {
                        break;
                    } else if (k == first.length - 1 && clashes == 1) {
                        var builder = new StringBuilder();
                        for (int l = 0; l < first.length; l++) {
                            if (first[l] == second[l]) {
                                builder.append(first[l]);
                            }
                        }

                        return builder;
                    }
                }
            }
        }

        return null;
    }

    private int containsChar(String word, int times) {
        return toInt(
                Stream.of(word.split(""))
                        .map(str -> str.charAt(0))
                        .anyMatch(c -> CharMatcher.is(c).countIn(word) == times)
        );
    }
}
