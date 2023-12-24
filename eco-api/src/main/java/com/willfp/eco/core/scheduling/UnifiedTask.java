package com.willfp.eco.core.scheduling;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface UnifiedTask {
    @NotNull Plugin getOwningPlugin();

    boolean isRepeatingTask();

    int getTaskId();

    boolean isSync();

    boolean isCancelled();

    void cancel();

    boolean isValid();
}
