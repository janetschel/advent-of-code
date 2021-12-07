package dev.janetschel.util.meta;

import com.google.common.io.CharStreams;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;


class Request {
    private static final String REQUEST_URL = "https://adventofcode.com";
    private static String SESSION_KEY = null;

    static String fetchInput(String year, String day) throws IOException {
        day = sanitizeDayForRequest(day);

        var url = new URL("%s/%s/day/%s/input".formatted(REQUEST_URL, year, day));
        var connection = url.openConnection();

        retrieveSessionKey();
        connection.setRequestProperty("Cookie", "session=%s".formatted(SESSION_KEY));

        return CharStreams.toString(new InputStreamReader(connection.getInputStream(), UTF_8));
    }

    @SneakyThrows
    private static void retrieveSessionKey() {
        if (SESSION_KEY != null) {
            // SESSION_KEY has already been read, no need to do it again
            return;
        }

        SESSION_KEY = File.readFile("src/main/resources/secret.yml")
                .split(": ")[1]
                .replaceAll("\"", "")
                .trim();
    }

    private static String sanitizeDayForRequest(String day) {
        // Hack to remove leading 0 in days
        return String.valueOf(parseInt(day));
    }
}
