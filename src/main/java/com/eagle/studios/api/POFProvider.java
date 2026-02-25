package com.eagle.studios.api;

import org.jetbrains.annotations.NotNull;

public final class POFProvider {

    private static POFAPI api;

    private POFProvider() {
    }

    @NotNull
    public static POFAPI getAPI() {
        if (api == null) {
            throw new IllegalStateException(
                    "PillarsOfFortune API is not loaded yet! Make sure POF is installed and your plugin depends on it."
            );
        }
        return api;
    }

    public static boolean isAvailable() {
        return api != null;
    }

    public static void register(@NotNull POFAPI instance) {
        if (api != null) {
            throw new IllegalStateException("PillarsOfFortune API is already registered!");
        }
        api = instance;
    }

    public static void unregister() {
        api = null;
    }
}
