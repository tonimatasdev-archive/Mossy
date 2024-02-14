package dev.tonimatas.mossy.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GameModeCommand extends Command {

    public GameModeCommand() {
        super("gamemode");

        ArgumentEnum<GameMode> gamemode = ArgumentType.Enum("gamemode", GameMode.class).setFormat(ArgumentEnum.Format.LOWER_CASED);
        gamemode.setCallback((sender, exception) -> sender.sendMessage(
                Component.text("Invalid gamemode ", NamedTextColor.RED)
                        .append(Component.text(exception.getInput(), NamedTextColor.WHITE))
                        .append(Component.text("!"))));

        ArgumentType.Entity("targets").onlyPlayers(true);

        setDefaultExecutor((sender, context) -> {
            String commandName = context.getCommandName();

            sender.sendMessage(Component.text("Usage: /" + commandName + " <gamemode> [targets]", NamedTextColor.RED));
        });

        addSyntax((sender, context) -> {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(Component.text("Please run this command in-game.", NamedTextColor.RED));
                return;
            }

            //Check permission, this could be replaced with hasPermission
            //if (p.getPermissionLevel() < 2) {
            //    sender.sendMessage(Component.text("You don't have permission to use this command.", NamedTextColor.RED));
            //    return;
            //}

            GameMode mode = context.get(gamemode);

            executeSelf(p, mode);
        }, gamemode);

    }

    private void executeSelf(Player sender, GameMode mode) {
        sender.setGameMode(mode);
        sender.sendMessage(Component.text("Tu modo de juego ahora es: " + mode.name()));
    }
}
