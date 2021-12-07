package dev.janetschel.calendar.year2021.day04;

import dev.janetschel.calendar.year2021.day04.models.Board;
import dev.janetschel.calendar.year2021.day04.models.WonBoardsConfiguration;
import dev.janetschel.interfaces.GenericPuzzle;

import java.util.Arrays;
import java.util.List;

import static dev.janetschel.calendar.year2021.day04.models.Board.boardFromInput;
import static dev.janetschel.util.datastructure.ListUtil.enqueue;
import static dev.janetschel.util.datastructure.ListUtil.list;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> input) {
        var nums = getDrawnNumbers(input);
        var boards = initializeBoards(input);

        for (var num : nums) {
            for (var board : boards) {
                board.updateDrawnNumber(num);

                if (board.hasBoardWon()) {
                    return board.calculateResultForBoard(num);
                }
            }
        }

        return -1L;
    }

    public Long solvePart2(List<String> input) {
        var nums = getDrawnNumbers(input);
        var boards = initializeBoards(input);
        var configuration = new WonBoardsConfiguration(null, 0L, list(Board.class));

        for (var num : nums) {
            for (var board : boards) {
                board.updateDrawnNumber(num);

                if (board.hasBoardWon() && configuration.handleBoardWin(boards, board)) {
                    return configuration.getLastBoardToWin().calculateResultForBoard(num);
                }
            }
        }

        return -1L;
    }

    private List<Long> getDrawnNumbers(List<String> input) {
        return Arrays.stream(input.get(0).split(","))
                .map(Long::parseLong)
                .toList();
    }

    private List<Board> initializeBoards(List<String> input) {
        var lines = enqueue(input, "");
        var boards = list(Board.class);
        var current = list(String.class);

        for (var i = 2; i < lines.size(); i++) {
            if (!lines.get(i).equals("")) {
                current.add(lines.get(i));
                continue;
            }

            boards.add(boardFromInput(current));
            current.clear();
        }

        return boards;
    }
}
