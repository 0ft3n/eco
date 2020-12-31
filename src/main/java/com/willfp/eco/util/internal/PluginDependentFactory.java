package com.willfp.eco.util.internal;

import com.willfp.eco.util.plugin.AbstractEcoPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public abstract class PluginDependentFactory extends PluginDependent {
    protected PluginDependentFactory(@NotNull final AbstractEcoPlugin plugin) {
        super(plugin);
    }
}
