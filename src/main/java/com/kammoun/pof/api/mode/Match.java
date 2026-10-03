package com.kammoun.pof.api.mode;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Set;

/**
 * A read-only view of one running match, handed to the mode handlers attached to it.
 * <p>
 * Every match owns its own world, so the world, its border, its time and its weather belong to this match
 * alone and a mode may change them freely through Bukkit without touching anybody else's game. Nothing on
 * this interface changes the match itself: a mode acts on the world and the players it finds here, and POF
 * keeps control of joining, elimination and the state machine.
 *
 * @see ItemModeHandler
 * @see MapModeHandler
 */
@ApiStatus.NonExtendable
public interface Match {

    /**
     * @return the id of the arena this is an instance of, for example {@code skylands}
     */
    @NotNull
    String getArenaId();

    /**
     * Several matches of one arena can run at once, each in its own world.
     *
     * @return the world this match is being played in
     */
    @NotNull
    World getWorld();

    /**
     * Spawns and levels are stored relative to this point, so a mode that places blocks should measure from
     * here rather than from world coordinates it has seen before.
     *
     * @return the centre the arena schematic was pasted at
     */
    @NotNull
    Location getCenter();

    /**
     * @return every team, in pillar order, including the ones already eliminated
     */
    @NotNull
    @Unmodifiable
    List<MatchTeam> getTeams();

    /**
     * @param player the player to look up
     * @return the team the player is seated on, or null if they are spectating or not in this match
     */
    @Nullable
    MatchTeam getTeam(@NotNull Player player);

    /**
     * @return everyone in this match who has not been eliminated, online only
     */
    @NotNull
    @Unmodifiable
    Set<Player> getAlivePlayers();

    /**
     * @return everyone in this match, alive, eliminated or spectating, online only
     */
    @NotNull
    @Unmodifiable
    Set<Player> getPlayers();

    /**
     * @return the Y of the pillar tops
     */
    int getGroundLevel();

    /**
     * @return the Y below which a player is eliminated
     */
    int getVoidLevel();

    /**
     * @return the highest Y players may build to
     */
    int getBuildLimit();

    /**
     * @return how many seconds the match has been running, counted from the moment the cages opened
     */
    int getElapsedSeconds();

    /**
     * @return how many seconds are left before the match times out, or -1 if it has no time limit
     */
    int getRemainingSeconds();
}
