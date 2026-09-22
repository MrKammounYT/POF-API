package com.kammoun.pof.api.event;

import org.bukkit.event.Cancellable;

/**
 * Base class for POF events fired before an action, which a plugin can cancel to stop it.
 * <p>
 * Cancelling only stops the action. POF tells the player nothing on your behalf, so send your own message.
 */
public abstract class POFCancellableEvent extends POFEvent implements Cancellable {

    private boolean cancelled;

    protected POFCancellableEvent() {
        super();
    }

    /**
     * @param async true if the event is fired off the main thread
     */
    protected POFCancellableEvent(boolean async) {
        super(async);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
