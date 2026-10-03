package com.kammoun.pof.api.mode;

import org.jetbrains.annotations.NotNull;

/**
 * One item mode attached to one running match.
 * <p>
 * Created by {@link ItemMode#createHandler}, so anything it remembers is about that match alone. Every method
 * runs on the main thread.
 */
public interface ItemModeHandler {

    /**
     * Hands out one round of items. Called every {@link #getRoundInterval} seconds while the match is being
     * played, which is the arena's {@code timers.item-interval} unless the mode says otherwise.
     *
     * @param delivery the round: who it is for, where the items come from, and how to hand them over
     */
    void deliver(@NotNull ItemDelivery delivery);

    /**
     * How many seconds apart this mode wants its rounds. POF runs its item timer, and the boss bar that counts
     * it down, at whatever this returns, so a mode whose rounds come on a rhythm of its own should say so here
     * rather than keep a second clock in {@link #onTick}. Asked once, when the match starts.
     *
     * @param arenaInterval the arena's own {@code timers.item-interval}
     * @return the seconds between two calls to {@link #deliver}; anything below one is read as one
     */
    default int getRoundInterval(int arenaInterval) {
        return arenaInterval;
    }

    /**
     * Called once a second while the match is being played, whether or not items are due this second. A mode
     * that does something besides handing out items on a rhythm of its own, such as moving players about, keeps
     * time here.
     * <p>
     * The delivery is the same one {@link #deliver} would be handed, so a mode may hand out or clear items on
     * its own schedule rather than only when the item timer fires.
     *
     * @param elapsedSeconds how long the match has been running
     * @param delivery       who is playing, where items come from, and how to hand them over
     */
    default void onTick(int elapsedSeconds, @NotNull ItemDelivery delivery) {
    }

    /**
     * Called when the match ends, however it ends, so a mode can put back anything it changed. The world is
     * deleted shortly afterwards, so blocks need no cleaning up.
     */
    default void onEnd() {
    }
}
