package com.kammoun.pof.api;

import com.kammoun.pof.api.registry.Registry;
import com.kammoun.pof.api.registry.RegistryKey;
import net.kyori.adventure.key.Keyed;
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
}
