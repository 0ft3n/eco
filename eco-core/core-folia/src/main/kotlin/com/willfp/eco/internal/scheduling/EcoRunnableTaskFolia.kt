package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.scheduling.RunnableTask
import com.willfp.eco.core.scheduling.UnifiedTask
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import java.util.concurrent.TimeUnit

abstract class EcoRunnableTaskFolia(protected val plugin: EcoPlugin) : RunnableTask {
    private var task: UnifiedTask? = null

    @Synchronized
    override fun runTask(): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getGlobalRegionScheduler().run(plugin) { this.run() })
        return this.task!!
    }

    @Synchronized
    override fun runTask(entity: Entity): UnifiedTask? {
        this.task = UnifiedTaskFolia(entity.scheduler.run(plugin, { this.run() }, { }))
        return this.task
    }

    @Synchronized
    override fun runTask(location: Location): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getRegionScheduler().run(plugin, location) { this.run() })
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
    override fun runTaskLater(entity: Entity, delay: Long): UnifiedTask? {
        this.task = UnifiedTaskFolia(entity.scheduler.runDelayed(plugin, { this.run() }, { }, delay))
        return this.task
    }

    @Synchronized
    override fun runTaskLater(location: Location, delay: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getRegionScheduler().runDelayed(plugin, location, { this.run() }, delay))
        return this.task!!
    }

    @Synchronized
    override fun runTaskLaterAsynchronously(delay: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getAsyncScheduler().runDelayed(plugin, { this.run() }, delay/20,
            TimeUnit.SECONDS))
        return this.task!!
    }

    @Synchronized
    override fun runTaskTimer(delay: Long, period: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, { this.run() },
            delay, period))
        return this.task!!
    }

    @Synchronized
    override fun runTaskTimer(entity: Entity, delay: Long, period: Long): UnifiedTask? {
        this.task = UnifiedTaskFolia(entity.scheduler.runAtFixedRate(plugin, { this.run() }, { }, delay, period))
        return this.task
    }

    @Synchronized
    override fun runTaskTimer(location: Location, delay: Long, period: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getRegionScheduler().runAtFixedRate(plugin, location, { this.run() }, delay,
            period))
        return this.task!!
    }

    @Synchronized
    override fun runTaskTimerAsynchronously(delay: Long, period: Long): UnifiedTask {
        this.task = UnifiedTaskFolia(Bukkit.getAsyncScheduler().runAtFixedRate(plugin, { this.run() },
            delay * 50, period * 50,
            TimeUnit.MILLISECONDS))
        return this.task!!
    }

    override fun cancel() {
        this.task?.cancel()
        this.task = null
    }
}