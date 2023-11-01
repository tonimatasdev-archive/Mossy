package net.tonimatasdev.mossy.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void info(String message) {
        send("\u001B[32m", "INFO", message);
    }

    public static void warn(String message) {
        send("\u001B[33m", "WARN", message);
    }

    public static void error(String message) {
        send("\u001B[31m", "ERROR", message);
    }

    private static void send(String color, String type, String message) {
        System.out.println(color + "[" + getDate() + "] [" + type + "]: " + message);
        System.out.print("\u001B[0m");
    }

    private static String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
