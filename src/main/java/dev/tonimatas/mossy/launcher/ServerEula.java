package dev.tonimatas.mossy.launcher;


import dev.tonimatas.mossy.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

//A straight-up copy of the minecraft ServerEula class (well except the SharedConstants).
public class ServerEula {
    private final Path file;
    private final boolean agreed;

    public ServerEula(Path path) {
        this.file = path;
        this.agreed = this.readFile();
    }

    private boolean readFile() {
        try (InputStream inputstream = Files.newInputStream(this.file)) {
            Properties properties = new Properties();
            properties.load(inputstream);
            return Boolean.parseBoolean(properties.getProperty("eula", "false"));
        } catch (Exception exception) {
            this.saveDefaults();
            return false;
        }
    }

    public boolean hasAgreedToEULA() {
        return this.agreed;
    }

    private void saveDefaults() {
        try (OutputStream outputstream = Files.newOutputStream(this.file)) {
            Properties properties = new Properties();
            properties.setProperty("eula", "false");
            properties.store(outputstream, "By changing the setting below to TRUE you are indicating your agreement to our EULA (https://account.mojang.com/documents/minecraft_eula).");
        } catch (Exception exception) {
            Logger.error("Could not save " + this.file + ": " + exception.getMessage());
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean checkEula(Path path, boolean accepteula) throws IOException {
        File file = path.toFile();
        ServerEula eula = new ServerEula(path);
        
        if (!eula.hasAgreedToEULA()) {
            int wrong = 0;

            Scanner console = null;
            if (!accepteula) {
                Logger.warn("WARNING: It appears you have not agreed to the EULA.\nPlease read the EULA (https://account.mojang.com/documents/minecraft_eula) and type 'yes' to continue.");
                System.out.print("Do you accept? (yes/no): ");
                console = new Scanner(System.in);
            }
            
            while (true) {
                String answer = console != null ? console.nextLine() : "yes";
                if (answer == null || answer.isBlank()) {
                    if (wrong++ >= 2) {
                        Logger.error("You have typed the wrong answer too many times. Exiting.");
                        return false;
                    }
                    Logger.info("Please type 'yes' or 'no'.");
                    System.out.print("Do you accept? (yes/no): ");
                    continue;
                }

                switch (answer.toLowerCase()) {
                    case "y", "yes" -> {
                        file.delete();
                        file.createNewFile();
                        try (FileWriter writer = new FileWriter(file)) {
                            writer.write("eula=true");
                        }
                        return true;
                    }
                    case "n", "no" -> {
                        Logger.error("You must accept the EULA to continue. Exiting.");
                        return false;
                    }
                    default -> {
                        if (wrong++ >= 2) {
                            Logger.error("You have typed the wrong answer too many times. Exiting.");
                            return false;
                        }
                        Logger.info("Please type 'yes' or 'no'.");
                        System.out.print("Do you accept? (yes/no): ");
                    }
                }
            }
        } else {
            return true;
        }
    }
}
