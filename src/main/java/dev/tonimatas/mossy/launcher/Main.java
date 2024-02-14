package dev.tonimatas.mossy.launcher;

import dev.tonimatas.mossy.Mossy;
import dev.tonimatas.mossy.logger.Logger;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        LibraryInstaller.init();

        try {
            if (!ServerEula.checkEula(Path.of("eula.txt"))) return;
        } catch (IOException e) {
            Logger.error("Error on load eula.");
            throw new RuntimeException(e);
        }

        Mossy.main(args);
    }
}
