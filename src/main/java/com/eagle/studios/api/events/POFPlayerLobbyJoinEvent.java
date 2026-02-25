package com.eagle.studios.api.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class POFPlayerLobbyJoinEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    private final Player player;

    public POFPlayerLobbyJoinEvent(@NotNull Player player) {
        this.player = player;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
