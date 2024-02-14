package dev.tonimatas.mossy.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.instance.Instance;

/**
 * Save the server
 */
public class SaveAllCommand extends Command {
    public SaveAllCommand() {
        super("save-all");
        setDefaultExecutor(this::execute);
    }
    private void execute(CommandSender sender, CommandContext arguments) {
        MinecraftServer.getInstanceManager().getInstances().forEach(Instance::saveChunksToStorage);
        sender.sendMessage("All words saved");
    }
}
