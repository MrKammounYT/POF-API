package com.kammoun.pof.api.mode;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * One map mode attached to one running match.
 * <p>
 * Created by {@link MapMode#createHandler}, so anything it remembers is about that match alone. Every method
 * runs on the main thread, which is where Bukkit block changes belong.
 */
public interface MapModeHandler {

    /**
     * Called once when the cages open, before the first tick.
     */
    default void onStart() {
    }

    /**
     * Called once a second while the match is being played. A mode with a slower rhythm counts the seconds
     * itself rather than asking to be called less often.
     *
     * @param elapsedSeconds how long the match has been running
     */
    void onTick(int elapsedSeconds);

    /**
     * Called after a living player places a block that POF allowed, so a mode can remember what is the map and
     * what somebody built.
     *
     * @param player the player who placed it
     * @param block  the block as it now stands in the world
     */
    default void onBlockPlaced(@NotNull Player player, @NotNull Block block) {
    }

    /**
     * Called whenever a living player moves, which is many times a second for every player in the match, so
     * this must stay cheap: check a coordinate, do not walk the world. Use {@link #onTick} for anything that
     * only needs to happen once a second.
     *
     * @param player the player who moved
     * @param to     where they have moved to
     */
    default void onPlayerMoved(@NotNull Player player, @NotNull Location to) {
    }

    /**
     * Called when the match ends, however it ends. The world is deleted shortly afterwards, so anything placed
     * in it needs no clearing up; this is for tasks, listeners or bars a mode opened elsewhere.
     */
    default void onEnd() {
    }
}
