package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.scheduling.UnifiedTask
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.plugin.Plugin

class UnifiedTaskFolia(private val handle: ScheduledTask): UnifiedTask {
    override fun getOwningPlugin(): Plugin {
        return handle.owningPlugin
    }

    override fun isRepeatingTask(): Boolean {
        return handle.isRepeatingTask
    }

    override fun getTaskId(): Int {
        return -1
    }

    override fun isSync(): Boolean {
        return true
    }

    override fun isCancelled(): Boolean {
        return handle.isCancelled
    }

    override fun cancel() {
        handle.cancel()
    }

    fun getExecutionState(): ScheduledTask.ExecutionState {
        return handle.executionState
    }
}