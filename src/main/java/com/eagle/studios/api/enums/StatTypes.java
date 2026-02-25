package com.eagle.studios.api.enums;

import java.util.Optional;

public enum StatTypes {

    // --- Currency ---
    COINS_SET,
    COINS_ADD,
    COINS_REMOVE,

    POINTS_SET,
    POINTS_ADD,
    POINTS_REMOVE,

    // --- Core Combat/Game Stats ---
    KILLS_SET,
    KILLS_ADD,
    KILLS_REMOVE,

    DEATHS_SET,
    DEATHS_ADD,
    DEATHS_REMOVE,

    WINS_SET,
    WINS_ADD,
    WINS_REMOVE,

    DRAWS_SET,
    DRAWS_ADD,
    DRAWS_REMOVE,

    GAMES_PLAYED_SET,
    GAMES_PLAYED_ADD,
    GAMES_PLAYED_REMOVE,

    // --- Streaks ---
    WIN_STREAK_SET,
    WIN_STREAK_ADD,
    WIN_STREAK_REMOVE,

    MAX_WIN_STREAK_SET;


    public static Optional<StatTypes> fromString(String text) {
        if (text == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(StatTypes.valueOf(text.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
