package com.eagle.studios.api.events;

import com.eagle.studios.api.enums.StatTypes;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class PofPlayerStatsChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;

    @NotNull
    private final StatTypes statType;

    public PofPlayerStatsChangeEvent(Player player, @NotNull StatTypes statTypes) {
        this.player = player;
        this.statType = statTypes;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
