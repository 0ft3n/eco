package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.scheduling.RunnableTask
import com.willfp.eco.core.scheduling.UnifiedTask
import org.bukkit.Bukkit
import java.util.concurrent.TimeUnit

abstract class EcoRunnableTaskFolia(protected val plugin: EcoPlugin) : RunnableTask {
    private var task: UnifiedTask? = null

    @Synchronized
    override fun runTask(): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getGlobalRegionScheduler().run(plugin) { this.run() })
        return this.task!!
    }

    @Synchronized
    override fun runTaskAsynchronously(): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getAsyncScheduler().runNow(plugin) { this.run() })
        return this.task!!
    }

    @Synchronized
    override fun runTaskLater(delay: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getGlobalRegionScheduler().runDelayed(plugin, { this.run() }, delay))
        return this.task!!
    }

    @Synchronized
    override fun runTaskLaterAsynchronously(delay: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getAsyncScheduler().runDelayed(plugin, { this.run() }, delay/20, TimeUnit.SECONDS))
        return this.task!!
    }

    @Synchronized
    override fun runTaskTimer(delay: Long, period: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, { this.run() }, delay, period))
        return this.task!!
    }

    @Synchronized
    override fun runTaskTimerAsynchronously(delay: Long, period: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getAsyncScheduler().runAtFixedRate(plugin, { this.run() }, delay/20, period/20,
            TimeUnit.SECONDS))
        return this.task!!
    }

    override fun cancel() {
        this.task?.cancel()
        this.task = null
    }
}