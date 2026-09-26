package com.kammoun.pof.api.mode;

import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

/**
 * A rule change applied to a match, for example {@code pof:no_pvp} or {@code pof:double_items}.
 * <p>
 * Registered in the {@link com.kammoun.pof.api.registry.RegistryKey#GAME_MODE} registry. Unlike an item mode,
 * any number of game modes can be active at once, so one must not assume it is alone.
 * <p>
 * Not to be confused with {@link org.bukkit.GameMode}, which is survival or creative. POF's game modes change
 * the rules of a match; how items are delivered is an {@link ItemMode} and what the map does is a
 * {@link MapMode}.
 * <p>
 * A mode is registered once and shared by every match on the server, so it must hold no state about any of
 * them: {@link #createHandler} makes one handler per match.
 */
public interface GameMode extends Keyed {

    /**
     * @return the name shown to players, for example in the vote menu
     */
    @NotNull
    Component getDisplayName();

    /**
     * Called once when the match starts being played.
     *
     * @param match    the match the handler will belong to
     * @param settings this mode's own section of {@code modes.yml}, empty rather than null when the file has
     *                 nothing to say about it
     * @return a handler for this match and no other
     */
    @NotNull
    GameModeHandler createHandler(@NotNull Match match, @NotNull ConfigurationSection settings);
}
