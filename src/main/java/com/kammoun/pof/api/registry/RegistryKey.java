package com.kammoun.pof.api.registry;

import com.kammoun.pof.api.POFAPI;
import com.kammoun.pof.api.mode.ItemMode;
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
     * How items are delivered during a match (Normal, Balanced, Shuffle, Swapper).
     */
    public static final RegistryKey<ItemMode> ITEM_MODE = create("item_mode", ItemMode.class);

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
