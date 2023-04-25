package com.willfp.eco.core.placeholder;

import com.willfp.eco.core.integrations.placeholder.PlaceholderManager;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a placeholder that can be registered.
 */
public interface RegistrablePlaceholder extends Placeholder {
    /**
     * Register the arguments.
     *
     * @return The arguments.
     */
    @NotNull
    default RegistrablePlaceholder register() {
        PlaceholderManager.registerPlaceholder(this);
        return this;
    }
}

