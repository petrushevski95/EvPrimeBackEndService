package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EvPrimeDateBuilder {

    public static String currentTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMMM, yyyy HH:mm:ss.SSS");
        return currentDateTime.format(formatter);
    }
}

