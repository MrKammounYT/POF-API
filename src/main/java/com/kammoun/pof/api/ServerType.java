package com.kammoun.pof.api;

/**
 * How POF runs on this server, chosen by the {@code serverType} config option.
 */
public enum ServerType {

    /**
     * Multiple arenas on one server. POF owns and protects the lobby world.
     */
    MULTIARENA,

    /**
     * Multiple arenas on a server that also hosts other minigames. POF does not protect the lobby world.
     */
    SHARED,

    /**
     * One arena per server behind a proxy. Joining the server joins the arena.
     */
    PROXY_LEGACY,

    /**
     * Many servers behind Velocity. Arenas are cloned automatically to keep free ones available.
     */
    PROXY
}
