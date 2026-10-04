package com.kammoun.pof.api.registry;

import com.kammoun.pof.api.POFAPI;
import com.kammoun.pof.api.achievement.Achievement;
import com.kammoun.pof.api.cosmetic.CelebrationEffectType;
import com.kammoun.pof.api.cosmetic.Cosmetic;
import com.kammoun.pof.api.kit.Kit;
import com.kammoun.pof.api.mode.ItemMode;
import com.kammoun.pof.api.mode.MapMode;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.NotNull;

/**
 * A typed handle for one of POF's registries, used with {@link POFAPI#getRegistry(RegistryKey)}.
 * <p>
 * Only POF defines registry keys. They are exposed as constants on this class.
 *
 * @param <T> the type of value in the registry
 */
public final class RegistryKey<T extends Keyed> implements Keyed {

    /**
     * How items are delivered during a match (Normal, Balanced, Shuffle, Swapper). One is active at a time.
     */
    public static final RegistryKey<ItemMode> ITEM_MODE = create("item_mode", ItemMode.class);

    /**
     * What the map does to a match (Rising Lava, Fragile Blocks). Any number can be active at once.
     */
    public static final RegistryKey<MapMode> MAP_MODE = create("map_mode", MapMode.class);

    /**
     * The kinds of effect a win celebration can be built from, naming what owners write as {@code type:}.
     */
    public static final RegistryKey<CelebrationEffectType> CELEBRATION_EFFECT =
            create("celebration_effect", CelebrationEffectType.class);

    /**
     * Starting loadouts a player can take into a match.
     */
    public static final RegistryKey<Kit> KIT = create("kit", Kit.class);

    /**
     * Cages, death cries, kill messages and win celebrations, all in one registry.
     */
    public static final RegistryKey<Cosmetic> COSMETIC = create("cosmetic", Cosmetic.class);

    /**
     * Achievements, both POF's own from {@code achievements.yml} and those addons register.
     */
    public static final RegistryKey<Achievement> ACHIEVEMENT = create("achievement", Achievement.class);

    private final Key key;
    private final Class<T> type;

    private RegistryKey(@NotNull Key key, @NotNull Class<T> type) {
        this.key = key;
        this.type = type;
    }

    private static <T extends Keyed> RegistryKey<T> create(@NotNull String value, @NotNull Class<T> type) {
        return new RegistryKey<>(Key.key(POFAPI.NAMESPACE, value), type);
    }

    /**
     * @return the key naming this registry, for example {@code pof:item_mode}
     */
    @Override
    public @NotNull Key key() {
        return key;
    }

    /**
     * @return the type of value in the registry
     */
    @NotNull
    public Class<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "RegistryKey[" + key.asString() + "]";
    }
}
