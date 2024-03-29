package dev.janetschel.calendar.year2021.day03;

import dev.janetschel.interfaces.GenericPuzzle;
import org.jooq.lambda.Seq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static dev.janetschel.util.converter.Converter.flipBits;
import static dev.janetschel.util.math.Math.toDecimal;
import static java.util.Comparator.reverseOrder;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        var gamma = getMostCommonCharactersAtEachPosition(input).stream()
                .reduce("", (str, current) -> str += current);

        var epsilon = flipBits(gamma);

        return toDecimal(gamma) * toDecimal(epsilon);
    }

    public Long solvePart2(List<String> input) {
        var most = filter(input, this::getMostCommonCharactersAtEachPosition);
        var least = filter(input, this::getLeastCommonCharactersAtEachPosition);

        return toDecimal(most) * toDecimal(least);
    }

    private String filter(List<String> input, Function<List<String>, List<String>> filterMethod) {
        for (var i = new AtomicInteger(0); i.get() < input.get(0).length() && input.size() > 1; i.incrementAndGet()) {
            var wanted = filterMethod.apply(input).get(i.get());
            input = input.stream()
                    .filter(bin -> String.valueOf(bin.charAt(i.get())).equals(wanted))
                    .toList();
        }

        return input.get(0);
    }

    private List<String> getMostCommonCharactersAtEachPosition(List<String> in) {
        var mostCommon = new ArrayList<String>();
        for (var i = new AtomicInteger(0); i.get() < in.get(0).length(); i.incrementAndGet()) {
            var current = (String) Seq.of(
                            in.stream()
                                    .map(bin -> String.valueOf(bin.charAt(i.get())))
                                    .sorted(reverseOrder()).toArray())
                    .mode()
                    .orElseThrow();

            mostCommon.add(current);
        }

        return mostCommon;
    }

    private List<String> getLeastCommonCharactersAtEachPosition(List<String> in) {
        return getMostCommonCharactersAtEachPosition(in).stream()
                .map(character -> character.equals("0") ? "1" : "0")
                .toList();
    }
}
