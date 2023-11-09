package net.tonimatasdev.mossy.launcher;

import net.tonimatasdev.mossy.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LibraryInstaller {
    public static void init() {
        long time = System.currentTimeMillis();
        List<JarFile> jarFiles = new ArrayList<>();

        for (String repository : LibraryManager.getResources(LibraryManager.Type.REPOSITORIES)) {
            for (String library : LibraryManager.getResources(LibraryManager.Type.LIBRARIES)) {
                String[] strings = library.split(":");
                String jarPath = strings[0].replace(".", "/") + "/" + strings[1] + "/" + strings[2];
                String jarName = strings[1] + "-" + strings[2] + ".jar";

                if (!new File("libraries/" + jarPath + "/" + jarName).exists()) downloadLibrary(repository + jarPath, jarPath, jarName);

                JarFile jarFile = null;
                try {
                    jarFile = new JarFile("libraries/" + jarPath + "/" + jarName);
                } catch (IOException ignored) {
                }

                if (jarFiles.contains(jarFile) || jarFile == null) continue;
                jarFiles.add(jarFile);
            }
        }

        for (JarFile jarFile : jarFiles) {
            try {
                Agent.appendJarFile(jarFile);
            } catch (IOException e) {
                Logger.error("Error on load libraries.");
                break;
            }
        }
        Logger.info("Libraries loaded. ( " + (System.currentTimeMillis() - time) + "ms)");
    }

    private static void downloadLibrary(String url, String jarDirectory, String jarName) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url + "/" + jarName).openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                new File("libraries/" + jarDirectory).mkdirs();
                FileOutputStream outputStream = new FileOutputStream("libraries/" + jarDirectory + "/" + jarName);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
                Logger.info("Library downloaded: " + jarName);
            }
        } catch (IOException e) {
            Logger.error("Error on download library: " + jarName);
        }
    }
}
