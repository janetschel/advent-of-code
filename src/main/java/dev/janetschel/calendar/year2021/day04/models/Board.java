package dev.janetschel.calendar.year2021.day04.models;

import dev.janetschel.util.datastructure.ListUtil;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

import static dev.janetschel.util.datastructure.ArrayUtil.rotateClockwise;

@Data
public class Board {
    private Number[][] board = new Number[5][5];

    public static Board boardFromInput(List<String> numbers) {
        return new Board(numbers);
    }

    private Board(List<String> input) {
        initializeBoard(input);
    }

    private void initializeBoard(List<String> numbers) {
        for (var i = 0; i < numbers.size(); i++) {
            var line = Arrays.stream(numbers.get(i).split(" "))
                    .map(current -> current.replaceAll(" ", ""))
                    .filter(current -> !"".equals(current))
                    .map(Long::parseLong)
                    .toList();

            for (var j = 0; j < line.size(); j++) {
                board[i][j] = new Number(line.get(j), false);
            }
        }
    }

    public void updateDrawnNumber(Long number) {
        for (var numbers : board) {
            for (Number value : numbers) {
                if (value.getValue().equals(number)) {
                    value.setDrawn(true);
                }
            }
        }
    }

    public Long calculateResultForBoard(Long lastNumber) {
        var sumOfNumbers = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(number -> !number.isDrawn())
                .map(Number::getValue)
                .reduce(0L, (sum, current) -> sum += current);

        return sumOfNumbers * lastNumber;
    }

    public boolean hasBoardWon() {
        // row || column
        return stream(board) || stream(rotateClockwise(board));
    }

    private boolean stream(Number[][] board) {
        return Arrays.stream(board)
                .map(List::of)
                .map(ListUtil::all)
                .filter(line -> line)
                .findFirst()
                .orElse(false);
    }
}
