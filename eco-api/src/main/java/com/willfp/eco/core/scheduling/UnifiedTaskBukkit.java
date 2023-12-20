package com.willfp.eco.core.scheduling;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class UnifiedTaskBukkit implements UnifiedTask {
    private BukkitTask handle;

    public UnifiedTaskBukkit(BukkitTask handle) {
        this.handle = handle;
    }


    @Override
    public @NotNull Plugin getOwningPlugin() {
        return handle.getOwner();
    }

    @Override
    public boolean isRepeatingTask() {
        return false;
    }

    @Override
    public int getTaskId() {
        return handle.getTaskId();
    }

    @Override
    public boolean isSync() {
        return handle.isSync();
    }

    @Override
    public boolean isCancelled() {
        return handle.isCancelled();
    }

    @Override
    public void cancel() {
        handle.cancel();
    }
}
