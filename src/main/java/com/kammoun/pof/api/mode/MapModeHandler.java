package com.kammoun.pof.api.mode;

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
     * Called when the match ends, however it ends. The world is deleted shortly afterwards, so anything placed
     * in it needs no clearing up; this is for tasks, listeners or bars a mode opened elsewhere.
     */
    default void onEnd() {
    }
}
