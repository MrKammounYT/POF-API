package com.eagle.studios.api.player;

import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public interface POFPlayer {

    // --- Identity & Core ---
    UUID getUuid();
    String getUsername();
    Player getPlayer();
    boolean isFireEvents();
    void setFireEvents(boolean fireEvents);

    // --- Statistics Getters ---
    int getPoints();
    int getCoins();
    int getKills();
    int getDeaths();
    int getWins();
    int getGamesPlayed();
    int getDraws();
    int getWinStreak();
    int getMaxWinStreak();
    int getLosses();
    double getKD();

    // --- Statistics Setters/Modifiers ---
    void setPoints(int points);
    void addPoints(int points);
    void setCoins(int coins);
    void addCoins(int amount);
    void removeCoins(int amount);
    void setVaultCoins(int newValue);
    boolean hasEnoughCoins(int amount);

    void setKills(int kills);
    void addKill();
    void addKills(int amount);

    void setDeaths(int deaths);
    void addDeath();
    void addDeaths(int amount);

    void setWins(int wins);
    void addWin();
    void addWins(int amount);

    void setGamesPlayed(int gamesPlayed);
    void addGamePlayed();

    void setDraws(int draws);
    void addDraw();

    void setWinStreak(int winStreak);
    void resetWinStreak();

    void setMaxWinStreak(int maxWinStreak);

}
