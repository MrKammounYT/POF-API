package com.eagle.studios.api.events;

import com.eagle.studios.api.arena.POFArena;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class POFPlayerElimination extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    @Nullable
    private final Player killer;
    private final EntityDamageEvent.DamageCause deathCause;
    private final POFArena arena;

    public POFPlayerElimination(Player player, @Nullable Player killer, EntityDamageEvent.DamageCause deathCause, POFArena arena) {
        this.player = player;
        this.killer = killer;
        this.deathCause = deathCause;
        this.arena = arena;
    }

    public POFPlayerElimination(Player player, EntityDamageEvent.DamageCause deathCause, POFArena arena) {
        this.player = player;
        this.killer = null;
        this.deathCause = deathCause;
        this.arena = arena;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
