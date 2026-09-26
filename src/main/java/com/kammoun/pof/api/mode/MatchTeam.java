package com.kammoun.pof.api.mode;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Set;

/**
 * One team in a running match, which is also one pillar: teammates share a spawn and a cage.
 * <p>
 * A solo match still has teams, one player each, so a mode never needs to special-case solo.
 */
@ApiStatus.NonExtendable
public interface MatchTeam {

    /**
     * @return this team's position in the match, counting from zero, which is also its pillar
     */
    int getIndex();

    /**
     * @return the team name, from the {@code teams} section of {@code config.yml}
     */
    @NotNull
    Component getDisplayName();

    /**
     * @return everyone seated on this team, whether still alive or not
     */
    @NotNull
    @Unmodifiable
    Set<Player> getMembers();

    /**
     * @return the members who have not been eliminated yet
     */
    @NotNull
    @Unmodifiable
    Set<Player> getAlivePlayers();

    /**
     * A team is out once every one of its players is, which is what ends a match rather than a single death.
     *
     * @return true if nobody on this team is still alive
     */
    boolean isEliminated();

    /**
     * @return the pillar this team starts on, in the match world
     */
    @NotNull
    Location getSpawn();
}
