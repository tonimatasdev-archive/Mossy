package net.tonimatasdev.mossy.extras;

import java.security.KeyPair;

public final class MojangAuth {
    public static final String AUTH_URL = System.getProperty("minestom.auth.url", "https://sessionserver.mojang.com/session/minecraft/hasJoined").concat("?username=%s&serverId=%s");
    private static volatile boolean enabled = false;
    private static volatile KeyPair keyPair;

    public static void init() {
        if (enabled) System.out.println("Mojang auth is already enabled!");
        //Check.stateCondition(MinecraftServer.process().isAlive(), "The server has already been started!");
        MojangAuth.enabled = true;
        // Generate necessary fields...
        MojangAuth.keyPair = MojangCrypt.generateKeyPair();
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static KeyPair getKeyPair() {
        return keyPair;
    }
}
