package dev.janetschel.calendar.year2020.day24;

import dev.janetschel.calendar.year2020.day24.models.Direction;
import dev.janetschel.calendar.year2020.day24.models.Tile;
import dev.janetschel.interfaces.GenericPuzzle;

import java.util.ArrayList;
import java.util.List;

public class Puzzle implements GenericPuzzle {
    public Long solvePart1(List<String> in) {
        return getTileBoard(in).stream()
                .filter(tile -> tile.getFacingUp() == Tile.Face.BLACK)
                .count();
    }

    // Solving conways game of tiles YAY! :)
    public Long solvePart2(List<String> in) {
        var board = getTileBoard(in);

        for (int i = 0; i < 1; i++) {
            var needsFlipping = new ArrayList<Tile>();

            // TODO: finish part 2 -> also tiles that are not in the board may need to be flipped
        }


        return board.stream()
                .filter(tile -> tile.getFacingUp() == Tile.Face.BLACK)
                .count();
    }

    private List<Tile> getTileBoard(List<String> in) {
        var movements = parseInput(in);
        var tiles = new ArrayList<Tile>();

        for (var movementLine : movements) {
            var tile = new Tile(0, 0, 0);
            tile.identifyTile(movementLine);

            for (Tile value : tiles) {
                if (value.equals(tile)) {
                    value.flipTile();
                    break;
                }
            }

            if (!tiles.contains(tile)) {
                tiles.add(tile);
            }
        }

        return tiles;
    }

    private List<List<Direction>> parseInput(List<String> input) {
        var allDirections = new ArrayList<List<Direction>>();
        for (var line : input) {
            var currentDirections = new ArrayList<Direction>();
            var chars = line.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'e') {
                    currentDirections.add(Direction.EAST);
                } else if (chars[i] == 's' && chars[i + 1] == 'e') {
                    currentDirections.add(Direction.SOUTHEAST);
                    i++;
                } else if (chars[i] == 's' && chars[i + 1] == 'w') {
                    currentDirections.add(Direction.SOUTHWEST);
                    i++;
                } else if (chars[i] == 'w') {
                    currentDirections.add(Direction.WEST);
                } else if (chars[i] == 'n' && chars[i + 1] == 'w') {
                    currentDirections.add(Direction.NORTHWEST);
                    i++;
                } else if (chars[i] == 'n' && chars[i + 1] == 'e') {
                    currentDirections.add(Direction.NORTHEAST);
                    i++;
                }
            }

            allDirections.add(currentDirections);
        }

        return allDirections;
    }
}
