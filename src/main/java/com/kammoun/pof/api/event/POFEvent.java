package com.kammoun.pof.api.event;

import org.bukkit.event.Event;

/**
 * Base class for every POF event, so a plugin can tell them apart from other events at a glance.
 * <p>
 * Events come in pairs: a cancellable one before an action ({@link POFCancellableEvent}) and a plain one after it
 * has happened. POF never fires an event to make something happen, so firing one yourself changes nothing.
 * <p>
 * Every concrete event keeps its own {@code HandlerList}, as Bukkit requires:
 * <pre>{@code
 * private static final HandlerList HANDLERS = new HandlerList();
 *
 * @Override
 * public @NotNull HandlerList getHandlers() {
 *     return HANDLERS;
 * }
 *
 * public static HandlerList getHandlerList() {
 *     return HANDLERS;
 * }
 * }</pre>
 */
public abstract class POFEvent extends Event {

    protected POFEvent() {
        super();
    }

    /**
     * @param async true if the event is fired off the main thread
     */
    protected POFEvent(boolean async) {
        super(async);
    }
}
