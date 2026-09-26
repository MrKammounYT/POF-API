package com.kammoun.pof.api.mode;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * One game mode attached to one running match.
 * <p>
 * Created by {@link GameMode#createHandler}, so anything it remembers is about that match alone. Every method
 * runs on the main thread.
 * <p>
 * Several handlers can be active at once and POF asks all of them, so a question is only answered the
 * permissive way by default: one handler saying no is enough to stop something, and no handler can force
 * something another has forbidden.
 */
public interface GameModeHandler {

    /**
     * Called once a second while the match is being played.
     *
     * @param elapsedSeconds how long the match has been running
     */
    default void onTick(int elapsedSeconds) {
    }

    /**
     * Whether one player may hurt another. Teammates already cannot, whatever this returns.
     *
     * @param victim   who would take the damage
     * @param attacker who would deal it
     * @return false to stop the hit; POF stops it if any handler says so
     */
    default boolean allowsPvp(@NotNull Player victim, @NotNull Player attacker) {
        return true;
    }

    /**
     * How many times the item round runs when the timer fires. Two gives everyone twice as many items without
     * the item mode knowing anything about it.
     *
     * @return the number of rounds, at least one; POF takes the largest any handler asks for
     */
    default int getItemRounds() {
        return 1;
    }

    /**
     * Called when the match ends, however it ends.
     */
    default void onEnd() {
    }
}
