package com.eagle.studios.api.events;

import com.eagle.studios.api.arena.POFArena;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class POFArenaItemGiveEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    private final POFArena arena;

    @NotNull
    private final Player player;

    @Setter
    private List<ItemStack> items;

    private final String gameMode;

    @Setter
    private boolean clearInventory;

    @Setter
    private boolean cancelled = false;

    public POFArenaItemGiveEvent(@NotNull POFArena arena, @NotNull Player player,
                                 @NotNull List<ItemStack> items, @NotNull String gameMode,
                                 boolean clearInventory) {
        this.arena = arena;
        this.player = player;
        this.items = items;
        this.gameMode = gameMode;
        this.clearInventory = clearInventory;
    }

    @Deprecated
    public POFArenaItemGiveEvent(@NotNull POFArena arena, @NotNull Player player) {
        this.arena = arena;
        this.player = player;
        this.items = List.of();
        this.gameMode = "NORMAL";
        this.clearInventory = false;
    }


    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
