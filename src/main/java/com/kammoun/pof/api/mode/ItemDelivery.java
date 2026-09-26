package com.kammoun.pof.api.mode;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Set;

/**
 * One round of the item timer, handed to an {@link ItemModeHandler} so it can decide who gets what.
 * <p>
 * An item mode never touches an inventory itself: it asks for items and calls {@link #give}, and POF decides
 * what happens when the inventory is full. That is also what lets a game mode run the same round twice.
 */
@ApiStatus.NonExtendable
public interface ItemDelivery {

    /**
     * @return the match this round belongs to
     */
    @NotNull
    Match getMatch();

    /**
     * @return the players this round is for: everyone still alive and online
     */
    @NotNull
    @Unmodifiable
    Set<Player> getRecipients();

    /**
     * Drawn from the pool in {@code items.yml}, so the server's blacklist and block stack size already apply.
     * Called once per item, so a mode that hands everybody the same thing should call it once and copy the
     * result rather than calling it per player.
     *
     * @return a fresh random item
     */
    @NotNull
    ItemStack nextRandomItem();

    /**
     * Hands an item to a player. Whatever does not fit is dropped at their feet.
     *
     * @param player the player to give it to, normally one of {@link #getRecipients()}
     * @param item   the item to give
     */
    void give(@NotNull Player player, @NotNull ItemStack item);

    /**
     * Empties a player's inventory of everything the match does not protect, for a mode that hands out a fresh
     * set of items rather than adding to the old one.
     * <p>
     * What survives is POF's decision, not the mode's: armor and the off hand always, and the slots of a kit
     * that asked to keep them. A mode therefore never needs to know what a kit is.
     *
     * @param player the player whose inventory to clear
     */
    void clearUnprotected(@NotNull Player player);
}
