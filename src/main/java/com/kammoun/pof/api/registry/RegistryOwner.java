package com.kammoun.pof.api.registry;

import org.jetbrains.annotations.NotNull;

/**
 * Whoever registered a value: POF itself, or an addon loaded from {@code plugins/POF/addons}.
 * <p>
 * Registries remember the owner of every value so all of them can be removed at once when an addon unloads.
 */
public interface RegistryOwner {

    /**
     * @return a name for log messages, for example the addon name
     */
    @NotNull
    String getName();
}
