package com.eagle.studios.api.events;

import com.eagle.studios.api.arena.POFArena;
import com.eagle.studios.api.enums.GameState;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class POFArenaStateChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    @NotNull
    private final POFArena arena;
    @NotNull
    private final GameState oldState;
    @NotNull
    private final GameState newState;

    public POFArenaStateChangeEvent(@NotNull POFArena arena, @NotNull GameState oldState, @NotNull GameState newState) {
        this.arena = arena;
        this.oldState = oldState;
        this.newState = newState;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
