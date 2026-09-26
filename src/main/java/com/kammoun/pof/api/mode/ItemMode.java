package com.kammoun.pof.api.mode;

import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

/**
 * How items are delivered to players during a match, for example {@code pof:normal} or {@code pof:shuffle}.
 * <p>
 * Registered in the {@link com.kammoun.pof.api.registry.RegistryKey#ITEM_MODE} registry. Exactly one item mode
 * is active in a match, chosen by the arena or by a vote.
 * <p>
 * A mode is registered once and is shared by every match on the server, so it must hold no state about any of
 * them: {@link #createHandler} makes one handler per match and that is where a countdown or a tally belongs.
 */
public interface ItemMode extends Keyed {

    /**
     * @return the name shown to players, for example in the vote menu
     */
    @NotNull
    Component getDisplayName();

    /**
     * Called once when a match reaches its first item round.
     *
     * @param match    the match the handler will belong to, already under way
     * @param settings this mode's own section of {@code modes.yml}, empty rather than null when the file has
     *                 nothing to say about it
     * @return a handler for this match and no other
     */
    @NotNull
    ItemModeHandler createHandler(@NotNull Match match, @NotNull ConfigurationSection settings);
}
