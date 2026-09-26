package com.kammoun.pof.api.mode;

import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

/**
 * Something the map does to a match, for example {@code pof:rising_lava} or {@code pof:fragile_blocks}.
 * <p>
 * Registered in the {@link com.kammoun.pof.api.registry.RegistryKey#MAP_MODE} registry. Any number can be
 * active at once, so one must not assume it is alone.
 * <p>
 * Because every match owns its world, a map mode may place blocks, move the border or change the weather
 * without affecting any other game, and needs no cleanup: the world is deleted when the match ends.
 * <p>
 * A mode is registered once and shared by every match on the server, so it must hold no state about any of
 * them: {@link #createHandler} makes one handler per match, and a rising lava level belongs there.
 */
public interface MapMode extends Keyed {

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
    MapModeHandler createHandler(@NotNull Match match, @NotNull ConfigurationSection settings);
}
