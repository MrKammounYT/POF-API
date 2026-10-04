package com.kammoun.pof.api.event;

import com.kammoun.pof.api.achievement.Achievement;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired on the main thread once a player has earned an achievement and POF has recorded it. Firing this event
 * yourself grants nothing; use {@link com.kammoun.pof.api.POFAPI#grantAchievement} for that.
 *
 * @see POFAchievementPreUnlockEvent
 */
public class POFAchievementUnlockEvent extends POFEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Achievement achievement;

    public POFAchievementUnlockEvent(@NotNull Player player, @NotNull Achievement achievement) {
        this.player = player;
        this.achievement = achievement;
    }

    /**
     * @return who earned it
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the achievement they earned
     */
    @NotNull
    public Achievement getAchievement() {
        return achievement;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
