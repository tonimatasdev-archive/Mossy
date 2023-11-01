package net.tonimatasdev.mossy.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.tonimatasdev.mossy.commands.*;
import net.tonimatasdev.mossy.logger.Logger;

public class CommandManager {
    public static void register() {
        net.minestom.server.command.CommandManager commandManager = MinecraftServer.getCommandManager();

        commandManager.register(new GameModeCommand());
        commandManager.register(new DifficultyCommand());
        commandManager.register(new MeCommand());
        commandManager.register(new SaveAllCommand());
        commandManager.register(new StopCommand());

        commandManager.setUnknownCommandCallback((sender, command) -> sender.sendMessage(Component.text("Unknown command", NamedTextColor.RED)));
        Logger.info("All commands registered.");
    }
}
