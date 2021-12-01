package dev.janetschel.calendar.day01;

import java.util.List;

import static dev.janetschel.utils.Converter.longs;
import static dev.janetschel.utils.File.read;

public class Puzzle {
    public static void main(String[] args) {
        var input = read("01");
        var result = new Puzzle().solve(input);

        System.out.println("result = " + result);
    }

    public String solve(List<String> in) {
        var input = longs(in);
        

        return null;
    }
}
