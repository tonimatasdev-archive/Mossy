package net.tonimatasdev.mossy.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.tonimatasdev.mossy.commands.*;
import net.tonimatasdev.mossy.logger.Logger;

public class MossyCommandManager {
    private static final CommandManager commandManager = MinecraftServer.getCommandManager();

    public static void init() {
        commandManager.register(new GameModeCommand());
        commandManager.register(new DifficultyCommand());
        commandManager.register(new MeCommand());
        commandManager.register(new SaveAllCommand());
        commandManager.register(new StopCommand());

        commandManager.setUnknownCommandCallback((sender, command) -> sender.sendMessage(Component.text("Unknown command", NamedTextColor.RED)));
        Logger.info("All commands registered.");
    }
}
