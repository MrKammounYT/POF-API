package com.kammoun.pof.api.cosmetic;

import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Something a player can unlock and select for looks alone, for example {@code pof:prison}.
 * <p>
 * Registered in the {@link com.kammoun.pof.api.registry.RegistryKey#COSMETIC} registry. POF defines its own
 * cosmetics in {@code cosmetics/}, so this interface only identifies one. A cosmetic never changes how a match
 * plays out.
 */
public interface Cosmetic extends Keyed {

    /**
     * @return the name shown to players in the cosmetics menu
     */
    @NotNull
    Component getDisplayName();

    /**
     * @return what part of a match this cosmetic dresses up
     */
    @NotNull
    CosmeticKind getKind();
}
