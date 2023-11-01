package net.tonimatasdev.mossy.launcher;

import net.tonimatasdev.mossy.Mossy;

public class Main {
    public static void main(String[] args) {
        LibraryInstaller.init();
        Mossy.main(args);
    }
}
