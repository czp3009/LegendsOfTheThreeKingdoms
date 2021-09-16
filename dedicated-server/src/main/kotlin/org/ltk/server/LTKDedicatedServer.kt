package org.ltk.server

import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.websocket.*
import mu.KotlinLogging
import org.ltk.server.room.RoomManager
import java.time.Duration

class LTKDedicatedServer(host: String = "0.0.0.0", port: Int = 8080) {
    private val roomManager = RoomManager()
    private val engine = embeddedServer(CIO, host = host, port = port) {
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(15)
            timeout = Duration.ofSeconds(15)
        }
        routing {
            webSocket("/") {
                logger.info { "connected!" }
                for (frame in incoming) {
                    send(frame)
                }
                logger.info { "disconnected!" }
            }
        }
    }

    fun start(wait: Boolean = true) = engine.start(wait)

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
