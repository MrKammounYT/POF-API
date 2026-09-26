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
     * Hands out one round of items. Called every {@code timers.item-interval} seconds while the match is being
     * played, and more than once in a row if a game mode says so.
     *
     * @param delivery the round: who it is for, where the items come from, and how to hand them over
     */
    void deliver(@NotNull ItemDelivery delivery);

    /**
     * Called once a second while the match is being played, whether or not items are due this second. A mode
     * with a rhythm of its own, such as wiping inventories every so often, keeps time here.
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
