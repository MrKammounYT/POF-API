package com.kammoun.pof.api;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
 * Static access to the {@link POFAPI}.
 * <p>
 * POF registers its API with Bukkit's services manager while it is enabled. Add {@code POF} to
 * {@code depend} or {@code softdepend} in your plugin.yml so the API is available when your plugin enables.
 */
public final class POFProvider {

    private POFProvider() {
    }

    /**
     * @return the POF API
     * @throws IllegalStateException if POF is not installed or not enabled
     */
    @NotNull
    public static POFAPI get() {
        POFAPI api = Bukkit.getServicesManager().load(POFAPI.class);
        if (api == null) {
            throw new IllegalStateException("The POF API is not available. Make sure POF is installed and enabled, "
                    + "and that your plugin lists POF in depend or softdepend.");
        }
        return api;
    }

    /**
     * @return true if POF is enabled and its API can be used
     */
    public static boolean isAvailable() {
        return Bukkit.getServicesManager().isProvidedFor(POFAPI.class);
    }
}
