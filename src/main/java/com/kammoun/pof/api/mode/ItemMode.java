package com.kammoun.pof.api.mode;

import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * How items are delivered to players during a match, for example {@code pof:normal} or {@code pof:shuffle}.
 * <p>
 * Registered in the {@link com.kammoun.pof.api.registry.RegistryKey#ITEM_MODE} registry. Addons add their own
 * by implementing this interface.
 * <p>
 * This interface only identifies a mode. The methods that decide which items to give, and when, arrive with the
 * Mode API in milestone 5.
 */
public interface ItemMode extends Keyed {

    /**
     * @return the name shown to players, for example in the vote menu
     */
    @NotNull
    Component getDisplayName();
}
