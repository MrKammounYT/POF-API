package com.kammoun.pof.api.event;

import com.kammoun.pof.api.achievement.Achievement;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired on the main thread just before a player earns an achievement, whether POF noticed it or an addon granted
 * it. Cancelling it leaves the achievement locked; it may be earned again the next time its trigger fires.
 *
 * @see POFAchievementUnlockEvent
 */
public class POFAchievementPreUnlockEvent extends POFCancellableEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Achievement achievement;

    public POFAchievementPreUnlockEvent(@NotNull Player player, @NotNull Achievement achievement) {
        this.player = player;
        this.achievement = achievement;
    }

    /**
     * @return who is about to earn it
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the achievement about to be earned
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
