package dev.janetschel.calendar.year2020.day24.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Tile {
    // ones tile is registered it started up but immediatly flipped around
    private Face facingUp = Face.BLACK;
    private int x;
    private int y;
    private int z;

    public Tile(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void identifyTile(List<Direction> directions) {
        for (var direction : directions) {
            step(direction);
        }
    }

    public void flipTile() {
        if (facingUp == Face.BLACK) {
            facingUp = Face.WHITE;
        } else {
            facingUp = Face.BLACK;
        }
    }

    private void step(Direction direction) {
        switch (direction) {
            case EAST -> {
                x++;
                y--;
            }
            case SOUTHEAST -> {
                x++;
                z--;
            }
            case SOUTHWEST -> {
                y++;
                z--;
            }
            case WEST -> {
                x--;
                y++;
            }
            case NORTHWEST -> {
                x--;
                z++;
            }
            case NORTHEAST -> {
                y--;
                z++;
            }
        }
    }

    public boolean isNeighbor(Tile tile) {
        return this != tile && // literally not the same object
                (
                        (x + 1 == tile.x && y - 1 == tile.y) || // east neighbor
                                (x + 1 == tile.x && z - 1 == tile.z) || // souteast
                                (y + 1 == tile.y && z - 1 == tile.z) || // southwest
                                (x - 1 == tile.x && y + 1 == tile.y) || // west
                                (x - 1 == tile.x && z + 1 == tile.z) || // northwest
                                (y - 1 == tile.y && z + 1 == tile.z)    // northeast
                );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y && z == tile.z;
    }

    public enum Face {
        WHITE,
        BLACK
    }
}
