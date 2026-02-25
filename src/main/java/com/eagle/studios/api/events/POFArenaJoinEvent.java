package com.eagle.studios.api.events;

import com.eagle.studios.api.arena.POFArena;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class POFArenaJoinEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    @NotNull
    private final POFArena arena;
    @NotNull
    private final Player player;

    public POFArenaJoinEvent(@NotNull POFArena arena, @NotNull Player player) {
        this.arena = arena;
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
