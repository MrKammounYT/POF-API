package com.kammoun.pof.api.cosmetic;

/**
 * The kinds of cosmetic POF has. Every kind shares one registry, one unlock system and one selection per player.
 */
public enum CosmeticKind {

    /**
     * The blocks a player is caged in before the match starts.
     */
    CAGE,

    /**
     * The sound played where a player is eliminated.
     */
    DEATH_CRY,

    /**
     * The wording shown when a player eliminates someone.
     */
    KILL_MESSAGE,

    /**
     * The effects played around a player who wins.
     */
    CELEBRATION
}
