package com.kammoun.pof.api;

/**
 * How many players share a team in a match.
 */
public enum TeamMode {

    SOLO(1),
    DUO(2),
    TRIO(3),
    SQUAD(4);

    private final int teamSize;

    TeamMode(int teamSize) {
        this.teamSize = teamSize;
    }

    /**
     * @return how many players fit in one team
     */
    public int getTeamSize() {
        return teamSize;
    }
}
