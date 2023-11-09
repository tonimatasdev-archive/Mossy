package net.tonimatasdev.mossy.launcher;

import net.tonimatasdev.mossy.logger.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    public static List<String> repositories = Arrays.asList(
            "https://jitpack.io/",
            "https://repo.maven.apache.org/maven2/");

    public static List<String> getLibraries() {
        List<String> libraries = new ArrayList<>();

        InputStream inputStream = Main.class.getResourceAsStream("/" + "libraries.txt");

        if (inputStream == null) {
            Logger.error("Error on get libraries.");
            return libraries;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            libraries.add(scanner.next());
        }

        scanner.close();
        return libraries;
    }
}
