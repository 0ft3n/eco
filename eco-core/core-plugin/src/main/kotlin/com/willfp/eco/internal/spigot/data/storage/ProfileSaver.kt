package com.willfp.eco.internal.spigot.data.storage

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.internal.spigot.data.EcoProfile
import com.willfp.eco.internal.spigot.data.ProfileHandler
import org.bukkit.Bukkit
import org.bukkit.Location

class ProfileSaver(
    private val plugin: EcoPlugin,
    private val handler: ProfileHandler
) {
    fun startTicking() {
        val interval = plugin.configYml.getInt("save-interval").toLong()

        plugin.scheduler.runTimerGlobally(20, interval.toInt()) {
            val iterator = EcoProfile.CHANGE_MAP.iterator()

            while (iterator.hasNext()) {
                val uuid = iterator.next()
                iterator.remove()

                val profile = handler.accessLoadedProfile(uuid) ?: continue

                handler.saveKeysFor(uuid, profile.data.keys)
            }
        }
    }
}
