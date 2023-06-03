package net.tonimatasdev.mossy.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.tonimatasdev.mossy.command.GameModeCommand;

public class Command {
    public static void register() {
        CommandManager commandManager = MinecraftServer.getCommandManager();

        commandManager.register(new GameModeCommand());

        commandManager.setUnknownCommandCallback((sender, command) -> sender.sendMessage(Component.text("Unknown command", NamedTextColor.RED)));
    }
}
