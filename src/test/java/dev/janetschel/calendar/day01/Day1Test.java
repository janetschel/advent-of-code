package dev.janetschel.calendar.day01;

import dev.janetschel.interfaces.GenericPuzzle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static dev.janetschel.utils.Converter.longs;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Day1Test {
    private static GenericPuzzle puzzle;

    @BeforeAll
    static void beforeTest() {
        puzzle = new Puzzle();
    }

    @Test
    void testPart1() {
        var in = """
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263""";

        var result = puzzle.solve(longs(split(in, "\n")));
        assertThat(result).isEqualTo(7);
    }

    @Test
    void testPart2() {
        var in = """
                607
                618
                618
                617
                647
                716
                769
                792""";

        var result = puzzle.solvePart2(longs(split(in, "\n")));
        assertThat(result).isEqualTo(5);
    }

    private static List<String> split(String in, String del) {
        return Arrays.stream(in.split(del))
                .toList();
    }
}
