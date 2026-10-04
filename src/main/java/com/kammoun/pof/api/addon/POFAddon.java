package com.kammoun.pof.api.addon;

import org.jetbrains.annotations.NotNull;

/**
 * The entry point of a POF addon: a jar in {@code plugins/POF/addons} that adds item modes, map modes,
 * celebration effects or achievements.
 * <p>
 * An addon is <strong>not</strong> a Bukkit plugin. POF loads it itself, each in its own class loader, and it
 * needs no {@code plugin.yml} — only an {@code addon.yml} naming this class:
 * <pre>
 * name: MyAddon
 * version: 1.0.0
 * main: com.example.myaddon.MyAddon
 * api-version: 3.0.0
 * </pre>
 * The class named by {@code main} must implement this interface and have a public no-argument constructor.
 * <p>
 * POF's own built-in modes are loaded through this same interface, so anything the built-ins can do an addon
 * can do too: if something is missing here, the API is incomplete rather than the addon at fault.
 * <p>
 * An addon that throws while loading is reported and skipped. It never stops POF from enabling, and never
 * stops another addon from loading.
 */
public interface POFAddon {

    /**
     * Called once while POF enables, after the registries exist and before any arena or match does. This is
     * where an addon registers what it brings.
     *
     * @param context what the addon may use: the API, its own identity to register under, a logger and a
     *                folder of its own
     */
    void onEnable(@NotNull AddonContext context);

    /**
     * Called while POF disables, before everything this addon registered is removed for it.
     * <p>
     * An addon need not unregister anything: POF removes everything registered under its owner. This is for
     * tasks, listeners or connections it opened itself.
     */
    default void onDisable() {
    }
}
