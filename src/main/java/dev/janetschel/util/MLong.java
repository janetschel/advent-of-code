package dev.janetschel.util;

// MaybeLong (more or less Optional<Long>)
public class MLong {
    private Long value; // can be null

    public MLong(Long value) {
        this.value = value;
    }

    public MLong(String value) {
        parse(value);
    }

    public static MLong mlong(String value) {
        return new MLong(value);
    }

    public MLong parse(String in) {
        try {
            value = Long.parseLong(in);
        } catch (NumberFormatException e) {
            value = null;
        }

        return this;
    }

    // Abstracting away the Long or → call to .or() can be made with .or(0) instead of .or(0L)
    public Long or(int or) {
        if (value == null) {
            return (long) or; // auto boxing to Long
        }

        return value;
    }
}
