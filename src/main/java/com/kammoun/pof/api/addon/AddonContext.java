package com.kammoun.pof.api.addon;

import com.kammoun.pof.api.POFAPI;
import com.kammoun.pof.api.registry.RegistryKey;
import com.kammoun.pof.api.registry.RegistryOwner;
import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * What one addon is given when it loads. Handed to {@link POFAddon#onEnable}.
 */
@ApiStatus.NonExtendable
public interface AddonContext {

    /**
     * @return POF itself, for the registries and anything else the API offers
     */
    @NotNull
    POFAPI getApi();

    /**
     * Everything this addon registers is recorded against this owner, so all of it can be removed at once when
     * the addon unloads.
     *
     * @return who POF considers to be registering
     */
    @NotNull
    RegistryOwner getOwner();

    /**
     * @return a logger that names this addon, so a server owner can see which addon spoke
     */
    @NotNull
    Logger getLogger();

    /**
     * {@code plugins/POF/addons/<name>}, created before the addon is enabled. An addon's own config files
     * belong here rather than beside the jar.
     *
     * @return a folder this addon owns
     */
    @NotNull
    Path getDataFolder();

    /**
     * Registers a value under this addon's own ownership, which is the short way of writing
     * {@code getApi().getRegistry(key).register(value, getOwner())}.
     *
     * @param key   which registry to add to, one of the constants on {@link RegistryKey}
     * @param value the value to register, under its own key
     * @param <T>   the type of value in the registry
     * @throws IllegalArgumentException if something is already registered under that value's key
     */
    <T extends Keyed> void register(@NotNull RegistryKey<T> key, @NotNull T value);
}
