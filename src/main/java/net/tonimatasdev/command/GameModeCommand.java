package net.tonimatasdev.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameModeCommand extends Command {

    public GameModeCommand() {
        super("gamemode");

        ArgumentEnum<GameMode> gamemode = ArgumentType.Enum("gamemode", GameMode.class).setFormat(ArgumentEnum.Format.LOWER_CASED);
        gamemode.setCallback((sender, exception) -> {
            sender.sendMessage(
                    Component.text("Invalid gamemode ", NamedTextColor.RED)
                            .append(Component.text(exception.getInput(), NamedTextColor.WHITE))
                            .append(Component.text("!")));
        });

        ArgumentEntity player = ArgumentType.Entity("targets").onlyPlayers(true);

        setDefaultExecutor((sender, context) -> {
            String commandName = context.getCommandName();

            sender.sendMessage(Component.text("Usage: /" + commandName + " <gamemode> [targets]", NamedTextColor.RED));
        });

        addSyntax((sender, context) -> {
            //Limit execution to players only
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

            //Set the gamemode for the sender
            executeSelf(p, mode);
        }, gamemode);

    }

    private void executeSelf(Player sender, GameMode mode) {
        sender.setGameMode(mode);

        //Send the translated message to the player.
        sender.sendMessage(Component.text("Tu modo de juego ahora es: " + mode.name()));
    }
}
