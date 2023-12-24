package com.willfp.eco.internal.scheduling

import com.willfp.eco.core.EcoPlugin
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import kotlinx.coroutines.runBlocking
import org.bukkit.entity.Entity
import java.util.concurrent.Future

class FoliaUtils {

}

inline fun <T> runEntity(plugin: EcoPlugin, entity: Entity, crossinline action: () -> T): T {

    var result: T? = null
    runBlocking {
        plugin.scheduler.run(entity) {
            result = action.invoke()
        }
    }

    return result!!
}