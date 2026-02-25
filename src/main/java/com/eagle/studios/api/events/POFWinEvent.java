package com.eagle.studios.api.events;

import com.eagle.studios.api.arena.POFArena;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class POFWinEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final POFArena arena;

    public POFWinEvent(Player player, POFArena arena) {
        this.player = player;
        this.arena = arena;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
