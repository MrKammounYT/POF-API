package com.eagle.studios.api.arena;

import com.eagle.studios.api.enums.GameState;
import org.bukkit.entity.Player;

import javax.annotation.concurrent.Immutable;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public interface POFArena {

    String getArenaName();

    String getDisplayName();

    int getMinPlayers();

    int getMaxPlayers();

    int getGameDuration();

    int getBorderSize();

    int getHeightLimit();

    int getGroundLevel();

    int getVoidLevel();

    GameState getGameState();

    Set<UUID> getPlayers();

    Set<UUID> getSpectators();

    boolean isPlayerInArena(Player player);

    boolean isSpectator(Player player);

    boolean isFull();

    int getTotalPlayingPlayers();

    Stream<Player> getOnlinePlayers();

    Stream<Player> getOnlineSpectators();

    Stream<Player> getEveryone();

    Player getWinner();

    boolean isValidToPlay();

    int getCountdown();

    void setGameState(GameState gameState);
}
