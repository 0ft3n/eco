package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.scheduling.RunnableTask
import com.willfp.eco.core.scheduling.UnifiedTask
import com.willfp.eco.core.scheduling.UnifiedTaskBukkit
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

abstract class EcoRunnableTask(protected val plugin: EcoPlugin) : BukkitRunnable(), RunnableTask {
    @Synchronized
    override fun runTask(): UnifiedTask {
        return UnifiedTaskBukkit(super.runTask(plugin))
    }

    @Synchronized
    override fun runTaskAsynchronously(): UnifiedTask {
        return UnifiedTaskBukkit(super.runTaskAsynchronously(plugin))
    }

    @Synchronized
    override fun runTaskLater(delay: Long): UnifiedTask {
        return UnifiedTaskBukkit(super.runTaskLater(plugin, delay))
    }

    @Synchronized
    override fun runTaskLaterAsynchronously(delay: Long): UnifiedTask {
        return UnifiedTaskBukkit(super.runTaskLaterAsynchronously(plugin, delay))
    }

    @Synchronized
    override fun runTaskTimer(delay: Long, period: Long): UnifiedTask {
        return UnifiedTaskBukkit(super.runTaskTimer(plugin, delay, period))
    }

    @Synchronized
    override fun runTaskTimerAsynchronously(delay: Long, period: Long): UnifiedTask {
        return UnifiedTaskBukkit(super.runTaskTimerAsynchronously(plugin, delay, period))
    }
}