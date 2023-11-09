package net.tonimatasdev.mossy.util;

import net.minestom.server.MinecraftServer;

import java.util.Scanner;

public class ConsoleThread extends Thread {
    private static boolean isStop;

    public static void stopConsole() {
        ConsoleThread.isStop = true;
    }

    @Override
    public void run() {
        while (!isStop) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            MinecraftServer.getCommandManager().executeServerCommand(command);
        }
    }
}
