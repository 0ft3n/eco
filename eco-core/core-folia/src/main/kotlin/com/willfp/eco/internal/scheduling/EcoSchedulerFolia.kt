package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.scheduling.Scheduler
import com.willfp.eco.core.scheduling.UnifiedTask
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import java.util.concurrent.TimeUnit

@Suppress("removal")
class EcoSchedulerFolia(private val plugin: EcoPlugin) : Scheduler {
    private val tasks = mutableListOf<UnifiedTask>()

    @Deprecated("Deprecated in Java")
    override fun runLater(runnable: Runnable, ticksLater: Long): UnifiedTask {
        return UnifiedTaskFolia(Bukkit.getGlobalRegionScheduler().runDelayed(plugin, { runnable.run() }, ticksLater.coerceAtLeast(1)))
    }

    override fun runLater(location: Location, ticksLater: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(Bukkit.getRegionScheduler().runDelayed(plugin, location, { task.run() }, ticksLater.toLong().coerceAtLeast(1)))
            .apply { tasks.add(this) }
    }

    override fun runLater(entity: Entity, ticksLater: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(entity.scheduler.runDelayed(plugin, { task.run() }, null, ticksLater.toLong().coerceAtLeast(1))!!)
            .apply { tasks.add(this) }
    }

    @Deprecated("Deprecated in Java")
    override fun runTimer(delay: Long, repeat: Long, runnable: Runnable): UnifiedTask {
        return UnifiedTaskFolia(Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, { runnable.run() }, delay.coerceAtLeast(1), repeat))
    }

    override fun runTimer(location: Location, delay: Int, repeat: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(Bukkit.getRegionScheduler().runAtFixedRate(plugin, location, { task.run() }, delay.toLong().coerceAtLeast(1), repeat.toLong().coerceAtLeast(1)))
            .apply { tasks.add(this) }
    }

    override fun runTimer(entity: Entity, delay: Int, repeat: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(entity.scheduler.runAtFixedRate(plugin, { task.run() }, null, delay.toLong().coerceAtLeast(1), repeat.toLong().coerceAtLeast(1))!!)
            .apply { tasks.add(this) }
    }

    @Deprecated("Deprecated in Java")
    override fun run(runnable: Runnable): UnifiedTask {
        return UnifiedTaskFolia(Bukkit.getGlobalRegionScheduler().run(plugin) { runnable.run() })
    }

    override fun run(location: Location, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(Bukkit.getRegionScheduler().run(plugin, location) { task.run() })
            .apply { tasks.add(this) }
    }

    override fun run(entity: Entity, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(
            entity.scheduler.run(plugin, { task.run() }, null)!!
        ).apply { tasks.add(this) }
    }

    override fun runAsync(task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(Bukkit.getAsyncScheduler().runNow(plugin) { task.run() })
    }

    override fun runGlobally(task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(
            Bukkit.getGlobalRegionScheduler().run(plugin) { task.run() }
        )
    }

    override fun runLaterGlobally(ticksLater: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(
            Bukkit.getGlobalRegionScheduler().runDelayed(plugin, { task.run() }, ticksLater.toLong())
        )
    }

    override fun runTimerGlobally(delay: Int, repeat: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(
            Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, { task.run() }, delay.toLong(), repeat.toLong())
        )
    }

    override fun runTimerAsync(delay: Int, repeat: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskFolia(Bukkit.getAsyncScheduler()
            .runAtFixedRate(plugin, { task.run() }, delay * 50L, repeat * 50L, TimeUnit.MILLISECONDS))
    }

    override fun cancelAll() {
        Bukkit.getAsyncScheduler().cancelTasks(plugin)
        Bukkit.getGlobalRegionScheduler().cancelTasks(plugin)
        for (task in tasks) {
            task.cancel()
        }
        tasks.clear()
    }
}
