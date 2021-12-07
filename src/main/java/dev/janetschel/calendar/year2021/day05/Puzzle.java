package dev.janetschel.calendar.year2021.day05;

import dev.janetschel.interfaces.GenericPuzzle;
import lombok.SneakyThrows;

import java.util.List;
import java.util.regex.Pattern;

import static dev.janetschel.calendar.year2021.day05.models.Instruction.init;
import static dev.janetschel.util.ArrayUtil.initSquareArray;

@SuppressWarnings("DuplicatedCode")
public class Puzzle implements GenericPuzzle {
    @SneakyThrows
    public Long solvePart1(List<String> input) {
        var lines = initSquareArray(1000);
        var pattern = Pattern.compile("(.*),(.*) -> (.*),(.*)");

        for (var instruction : input) {
            var m = pattern.matcher(instruction);
            if (!m.find()) {
                throw new Exception("No valid input line");
            }

            var in = init(m.group(1), m.group(2), m.group(3), m.group(4));
            if (in.isDiagonal()) {
                continue;
            }

            for (var i = Long.min(in.start().y(), in.end().y()); i <= Long.max(in.start().y(), in.end().y()); i++) {
                for (var j = Long.min(in.start().x(), in.end().x()); j <= Long.max(in.start().x(), in.end().x()); j++) {
                    lines[(int) i][(int) j]++;
                }
            }
        }

        var count = 0L;
        for (var line : lines) {
            for (var a : line) {
                if (a > 1) {
                    count++;
                }
            }
        }

        return count;
    }

    @SneakyThrows
    public Long solvePart2(List<String> input) {
        var lines = initSquareArray(1000);
        var pattern = Pattern.compile("(.*),(.*) -> (.*),(.*)");

        for (var instruction : input) {
            var m = pattern.matcher(instruction);
            if (!m.find()) {
                throw new Exception("No valid input line");
            }

            var in = init(m.group(1), m.group(2), m.group(3), m.group(4));
            if (in.isDiagonal()) {
                // Only difference to part 1
                var i = in.start().y();
                var j = in.start().x();

                if (j < in.end().x() && i < in.end().y()) {
                    while (i <= in.end().y()) {
                        lines[(int) i++][(int) j++]++;
                    }
                } else if (j < in.end().x() && i > in.end().y()) {
                    while (i >= in.end().y()) {
                        lines[(int) i--][(int) j++]++;
                    }
                } else if (j > in.end().x() && i < in.end().y()) {
                    while (i <= in.end().y()) {
                        lines[(int) i++][(int) j--]++;
                    }
                } else {
                    while (i >= in.end().y()) {
                        lines[(int) i--][(int) j--]++;
                    }
                }

                continue;
            }

            for (var i = Long.min(in.start().y(), in.end().y()); i <= Long.max(in.start().y(), in.end().y()); i++) {
                for (var j = Long.min(in.start().x(), in.end().x()); j <= Long.max(in.start().x(), in.end().x()); j++) {
                    lines[(int) i][(int) j]++;
                }
            }
        }

        var count = 0L;
        for (var line : lines) {
            for (var a : line) {
                if (a > 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
