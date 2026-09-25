package com.kammoun.pof.api.kit;

import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * A starting loadout a player can take into a match, for example {@code pof:starter}.
 * <p>
 * Registered in the {@link com.kammoun.pof.api.registry.RegistryKey#KIT} registry. POF's own kits are defined
 * in {@code kits.yml}, so this interface only identifies one: a kit is items, armor and potion effects handed
 * out when the match starts, never an ability or a cooldown.
 */
public interface Kit extends Keyed {

    /**
     * @return the name shown to players, for example in the kit menu
     */
    @NotNull
    Component getDisplayName();
}
