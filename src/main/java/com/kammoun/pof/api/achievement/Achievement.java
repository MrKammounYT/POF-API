package com.kammoun.pof.api.achievement;

import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * Something a player earns once and keeps, for example {@code pof:first_blood}.
 * <p>
 * Registered in the {@link com.kammoun.pof.api.registry.RegistryKey#ACHIEVEMENT} registry. POF defines its own
 * in {@code achievements.yml}, each with a trigger POF watches for. An addon registers its own the same way and
 * decides for itself when one is earned, then hands it out with
 * {@link com.kammoun.pof.api.POFAPI#grantAchievement}: POF does the rest, from storing it to announcing it.
 * <p>
 * An achievement can be what unlocks a kit or a cosmetic, which name it by key in their {@code unlock} block.
 */
public interface Achievement extends Keyed {

    /**
     * @return the name shown to players, in the achievements menu and when they earn it
     */
    @NotNull
    Component getDisplayName();

    /**
     * @return what a player has to do to earn it, one line each, shown in the achievements menu
     */
    @NotNull
    @Unmodifiable
    List<Component> getDescription();

    /**
     * @return the item the achievements menu shows it as
     */
    @NotNull
    Material getIcon();

    /**
     * A hidden achievement is listed in the menu without its name or description until it is earned, so it can
     * be a surprise.
     *
     * @return true to keep it secret until earned
     */
    default boolean isHidden() {
        return false;
    }
}
