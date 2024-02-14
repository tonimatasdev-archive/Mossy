package dev.tonimatas.mossy.api;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.CommandSender;
import net.minestom.server.permission.Permission;
import net.minestom.server.tag.TagHandler;
import dev.tonimatas.mossy.logger.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class ServerSender implements CommandSender {
    private final Set<Permission> permissions = Collections.unmodifiableSet(new HashSet<>());
    private final TagHandler tagHandler = TagHandler.newHandler();

    @Override
    public @NotNull Identity identity() {
        return Identity.nil();
    }

    @Override
    public @NotNull Set<Permission> getAllPermissions() {
        return permissions;
    }

    @Override
    public @NotNull TagHandler tagHandler() {
        return tagHandler;
    }

    @Override
    public void sendMessage(@NotNull String message) {
        Logger.info(message);
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        Logger.info(message.insertion());
    }
}
