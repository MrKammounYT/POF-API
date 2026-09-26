package com.kammoun.pof.api.cosmetic;

import net.kyori.adventure.key.Keyed;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A kind of effect a celebration can be built from, for example {@code pof:firework}.
 * <p>
 * Registered in the {@link com.kammoun.pof.api.registry.RegistryKey#CELEBRATION_EFFECT} registry. This is what
 * an addon implements to give server owners a new {@code type:} to write in
 * {@code cosmetics/celebrations.yml}; the six POF ships with are registered the same way.
 * <p>
 * The key's value is the name owners write, so {@code pof:firework} is reached by {@code type: firework} and an
 * addon's own type by the full {@code type: myaddon:confetti}.
 */
public interface CelebrationEffectType extends Keyed {

    /**
     * Reads one entry of a celebration's {@code effects} list.
     *
     * @param settings the keys written beside {@code type:}, which never includes {@code type} itself
     * @return the effect, or null to refuse the entry; POF then logs it and skips it, leaving the rest of the
     *         celebration intact
     */
    @Nullable
    CelebrationEffect create(@NotNull ConfigurationSection settings);
}
