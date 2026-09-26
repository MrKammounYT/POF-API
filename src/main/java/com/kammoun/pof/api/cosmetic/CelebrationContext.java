package com.kammoun.pof.api.cosmetic;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Set;

/**
 * One round of one winner's celebration, handed to every {@link CelebrationEffect} in their preset.
 * <p>
 * Each winner plays the celebration they chose, at their own feet, so a team of four can set off four
 * different ones. A celebration runs in rounds off the once-a-second tick of the finished match rather than on
 * a task of its own, which is why an effect should do one burst per call and never start something that could
 * outlive the match: the world is deleted moments later.
 */
@ApiStatus.NonExtendable
public interface CelebrationContext {

    /**
     * @return the winner whose celebration this is
     */
    @NotNull
    Player getPlayer();

    /**
     * @return where to play it, which is where the winner is standing
     */
    @NotNull
    Location getLocation();

    /**
     * @return everyone still in the match, winners and spectators alike
     */
    @NotNull
    @Unmodifiable
    Set<Player> getViewers();

    /**
     * @return which round this is, counting from zero
     */
    int getRound();

    /**
     * The server owner's ceiling from {@code cosmetics/settings.yml}. An effect that spawns particles must not
     * spawn more than this in one call, however many its own settings ask for.
     *
     * @return the most particles this effect may spawn per call
     */
    int getMaxParticles();

    /**
     * Prepares a line of text straight from the config: fills in the placeholders a celebration may use,
     * {@code %winner%} and {@code %arena%}, and translates the colour codes POF accepts, both {@code &a} and
     * {@code &#RRGGBB}. Every effect that shows text should pass its text through here, so owners can write
     * the same thing everywhere.
     *
     * @param raw text straight from the config
     * @return the text with its placeholders replaced and its colours translated to section signs, ready for
     *         {@code LegacyComponentSerializer.legacySection()} or {@code Player.sendMessage}
     */
    @NotNull
    String fill(@NotNull String raw);
}
