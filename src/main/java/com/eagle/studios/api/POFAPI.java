package com.eagle.studios.api;

import com.eagle.studios.api.arena.POFArena;
import com.eagle.studios.api.enums.GameState;
import com.eagle.studios.api.player.POFPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface POFAPI {

    // --- Player Access ---

    @Nullable
    POFPlayer getPlayer(@NotNull Player player);

    @Nullable
    POFPlayer getPlayer(@NotNull UUID uuid);

    // --- Arena Access ---

    @Nullable
    POFArena getArena(@NotNull String arenaName);

    @NotNull
    Collection<? extends POFArena> getArenas();

    // --- Arena Queries ---

    boolean isInArena(@NotNull Player player);

    @Nullable
    POFArena getPlayerArena(@NotNull Player player);

    @Nullable
    POFArena getNextAvailableArena();

    int getTotalArenas();
}
