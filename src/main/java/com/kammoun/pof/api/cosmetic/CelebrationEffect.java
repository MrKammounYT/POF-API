package com.kammoun.pof.api.cosmetic;

import org.jetbrains.annotations.NotNull;

/**
 * One effect inside a win celebration: a firework, a puff of particles, a sound, a line of text.
 * <p>
 * Built by a {@link CelebrationEffectType} from the settings an owner wrote in
 * {@code cosmetics/celebrations.yml}, so an instance holds the settings of one entry in that file and is
 * shared by every match that plays it. It must therefore keep no state about any one celebration; what it
 * needs comes in with the {@link CelebrationContext}.
 */
public interface CelebrationEffect {

    /**
     * Plays one burst. Called on the main thread, once per round of the celebration.
     *
     * @param context where to play it, for whom, and the owner's performance ceiling
     */
    void play(@NotNull CelebrationContext context);
}
