package dev.janetschel.calendar.year2021.day04.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class WonBoardsConfiguration {
    private Board lastBoardToWin;
    private Long wonBoards;
    private List<Board> alreadyWonBoards;

    /**
     * @return true, if this board was the last to win, false, if this board isn't the last to win OR it already won
     */
    public boolean handleBoardWin(List<Board> boards, Board board) {
        if (alreadyWonBoards.contains(board)) {
            return false;
        }

        alreadyWonBoards.add(board);
        lastBoardToWin = board;
        wonBoards++;

        return boards.size() == wonBoards;
    }
}
