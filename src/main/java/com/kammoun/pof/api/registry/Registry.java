package com.kammoun.pof.api.registry;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * A set of values identified by namespaced keys, for example {@code pof:rising_lava}.
 * <p>
 * Built-in values use the {@code pof} namespace. Other plugins should use their own namespace.
 * Registries are thread-safe and keep registration order.
 *
 * @param <T> the type of value in this registry
 */
@ApiStatus.NonExtendable
public interface Registry<T extends Keyed> {

    /**
     * @return the handle identifying this registry
     */
    @NotNull
    RegistryKey<T> getRegistryKey();

    /**
     * Registers a value under its own key.
     *
     * @param value the value to register
     * @throws IllegalArgumentException if a value with the same key is already registered
     */
    void register(@NotNull T value);

    /**
     * Removes the value registered under a key, for example when the plugin that registered it disables.
     *
     * @param key the key to remove
     * @return true if a value was removed
     */
    boolean unregister(@NotNull Key key);

    /**
     * @param key the key to look up
     * @return the value registered under the key, or empty if there is none
     */
    @NotNull
    Optional<T> get(@NotNull Key key);

    /**
     * @param key the key to check
     * @return true if a value is registered under the key
     */
    boolean contains(@NotNull Key key);

    /**
     * @return a snapshot of all keys, in registration order
     */
    @NotNull
    @Unmodifiable
    Set<Key> keys();

    /**
     * @return a snapshot of all values, in registration order
     */
    @NotNull
    @Unmodifiable
    Collection<T> values();

    /**
     * @return the number of registered values
     */
    int size();
}
