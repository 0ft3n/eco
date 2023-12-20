package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.scheduling.Scheduler
import com.willfp.eco.core.scheduling.UnifiedTask
import com.willfp.eco.core.scheduling.UnifiedTaskBukkit
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.scheduler.BukkitTask

class EcoSchedulerSpigot(private val plugin: EcoPlugin) : Scheduler {
    override fun runLater(location: Location, ticksLater: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTaskLater(plugin, task, ticksLater.toLong()))
    }

    override fun runLater(entity: Entity, ticksLater: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTaskLater(plugin, task, ticksLater.toLong()))
    }

    override fun runTimer(location: Location, delay: Int, repeat: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTaskTimer(plugin, task, delay.toLong(), repeat.toLong()))
    }

    override fun runTimer(entity: Entity, delay: Int, repeat: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTaskTimer(plugin, task, delay.toLong(), repeat.toLong()))
    }

    override fun run(location: Location, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTask(plugin, task))
    }

    override fun run(entity: Entity, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTask(plugin, task))
    }

    override fun runAsync(task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTaskAsynchronously(plugin, task))
    }

    override fun runGlobally(task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTask(plugin, task))
    }

    override fun runLaterGlobally(ticksLater: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTaskLater(plugin, task, ticksLater.toLong()))
    }

    override fun runTimerGlobally(delay: Int, repeat: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTaskTimer(plugin, task, delay.toLong(), repeat.toLong()))
    }

    override fun runTimerAsync(delay: Int, repeat: Int, task: Runnable): UnifiedTask {
        return UnifiedTaskBukkit(Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delay.toLong(), repeat.toLong()))
    }

    override fun cancelAll() {
        Bukkit.getScheduler().cancelTasks(plugin)
    }
}