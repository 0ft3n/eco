package com.willfp.eco.internal.spigot.metrics

import com.willfp.eco.core.Eco
import com.willfp.eco.core.config.json
import com.willfp.eco.core.data.ServerProfile
import com.willfp.eco.core.scheduling.Scheduler
import org.bukkit.Bukkit
import org.bukkit.Location
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private const val PLAYERFLOW_URL = "https://playerflow.auxilor.io/api/v1/ping"

private val client = HttpClient.newBuilder().build()

class PlayerflowHandler(
    private val scheduler: Scheduler
) {
    internal fun startTicking() {
        scheduler.runTimerAsync(1200, 1200) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        val body = json {
            "uuid" to ServerProfile.load().localServerID
            "players" to Bukkit.getOnlinePlayers().size
            "plugins" to Eco.get().loadedPlugins
        }.toPlaintext()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(PLAYERFLOW_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build()

        try {
            client.send(request, HttpResponse.BodyHandlers.ofString())
        } catch (e: Exception) {
            // Silently fail
        }
    }
}
