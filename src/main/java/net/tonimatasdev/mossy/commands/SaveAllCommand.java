package net.tonimatasdev.mossy.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.tonimatasdev.mossy.Mossy;

/**
 * Save the server
 */
public class SaveAllCommand extends Command {
    public SaveAllCommand() {
        super("save-all");
        setDefaultExecutor(this::execute);
    }
    private void execute(CommandSender player, CommandContext arguments) {
        MinecraftServer.getInstanceManager().getInstances().forEach(i -> {
            Mossy.save();
            //LOGGER.info("Saved dimension " + i.getDimensionType().getName());
        });
    }
}
