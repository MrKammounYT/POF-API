package com.kammoun.pof.api;

import com.kammoun.pof.api.registry.Registry;
import com.kammoun.pof.api.registry.RegistryKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Entry point to POF for other plugins. Get it with {@link POFProvider#get()}.
 */
@ApiStatus.NonExtendable
public interface POFAPI {

    /**
     * The namespace used by POF's built-in keys, for example {@code pof:rising_lava}.
     */
    String NAMESPACE = "pof";

    /**
     * @return the version of the running POF plugin, for example {@code 3.0.0}
     */
    @NotNull
    String getVersion();

    /**
     * @return how POF runs on this server
     */
    @NotNull
    ServerType getServerType();

    /**
     * @param key the registry to get, one of the constants on {@link RegistryKey}
     * @param <T> the type of value in the registry
     * @return the registry
     */
    @NotNull
    <T extends Keyed> Registry<T> getRegistry(@NotNull RegistryKey<T> key);

    /**
     * @param player      an online player
     * @param achievement the key of an achievement in the {@link RegistryKey#ACHIEVEMENT} registry
     * @return true if the player has earned it; false while their data is still loading after a join
     */
    boolean hasAchievement(@NotNull Player player, @NotNull Key achievement);

    /**
     * Gives a player an achievement, the way an addon hands out one whose trigger it watches itself.
     * <p>
     * POF fires {@link com.kammoun.pof.api.event.POFAchievementPreUnlockEvent} first, then records it, announces
     * it and fires {@link com.kammoun.pof.api.event.POFAchievementUnlockEvent}. An achievement earned during a
     * match is announced in chat at once, and with a title once the player is back in the lobby.
     *
     * @param player      an online player
     * @param achievement the key of an achievement in the {@link RegistryKey#ACHIEVEMENT} registry
     * @return true if the player earned it now; false if nothing is registered under the key, they already had
     *         it, their data is still loading, or a plugin cancelled it
     * @throws IllegalStateException if called off the main thread
     */
    boolean grantAchievement(@NotNull Player player, @NotNull Key achievement);
}
