package dev.janetschel.calendar.day05.models;

import static java.lang.Long.parseLong;

public record Instruction(Coordinate start, Coordinate end) {
    public static Instruction init(String... coords) {
        var start = new Coordinate(parseLong(coords[0]), parseLong(coords[1]));
        var end = new Coordinate(parseLong(coords[2]), parseLong(coords[3]));

        return new Instruction(start, end);
    }

    public boolean isDiagonal() {
        return start.x() != end.x() && start.y() != end.y();
    }

    public record Coordinate(long x, long y) {
    }
}
