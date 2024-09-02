package com.willfp.eco.internal.spigot.eventlisteners

import com.willfp.eco.core.Eco
import com.willfp.eco.core.EcoPlugin
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import java.util.concurrent.ConcurrentHashMap

class EntityDeathByEntityListeners(
    private val plugin: EcoPlugin
) : Listener {
    private val events = ConcurrentHashMap.newKeySet<EntityDeathByEntityBuilder>()

    @EventHandler(priority = EventPriority.HIGH)
    fun onEntityDamage(event: EntityDamageByEntityEvent) {
        if ((event.entity !is LivingEntity)) {
            return
        }

        val victim = event.entity as LivingEntity

        if (victim.health > event.finalDamage) {
            return
        }

        val builtEvent = EntityDeathByEntityBuilder()
        builtEvent.victim = victim
        builtEvent.damager = event.damager

        events += builtEvent

        // Eco.get().ecoPlugin.logger.info("Builder add for ${victim.uniqueId}")

        this.plugin.scheduler.runLaterGlobally(5) { // Fixes conflicts with WildStacker
            events.remove(builtEvent)
            // Eco.get().ecoPlugin.logger.info("Builder removed for ${victim.uniqueId}")
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onEntityDeath(event: EntityDeathEvent) {
        val victim = event.entity
        val drops = event.drops
        val xp = event.droppedExp

        var builtEvent: EntityDeathByEntityBuilder? = null

        // Eco.get().ecoPlugin.logger.info("Trying to find builder for ${victim.uniqueId}")

        try {
            for (builder in events) {
                if (builder.victim?.uniqueId == victim.uniqueId) {
                    builtEvent = builder
                    break
                }
            }
        } catch (ignored: ConcurrentModificationException) {
            // Eco.get().ecoPlugin.logger.info("Concurrent exception for ${victim.uniqueId}")
        }

        if (builtEvent == null) {
            // Eco.get().ecoPlugin.logger.info("Failed to find builder for ${victim.uniqueId}")
            return
        }

        // Eco.get().ecoPlugin.logger.info("Found builder for ${victim.uniqueId}")

        events.remove(builtEvent)
        builtEvent.drops = drops
        builtEvent.xp = xp
        builtEvent.deathEvent = event

        builtEvent.push()
    }
}
