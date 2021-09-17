package org.ltk.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.websocket.*
import mu.KotlinLogging
import org.ltk.server.game.GameModeHolder
import org.ltk.server.model.RestErrorResponse
import org.ltk.server.room.RoomManager
import org.ltk.server.room.model.CreateRoomRequest
import org.ltk.server.room.model.CreateRoomResponse
import java.time.Duration

class LTKDedicatedServer(host: String = "0.0.0.0", port: Int = 8080) {
    private val roomManager = RoomManager()
    private val engine = embeddedServer(CIO, host = host, port = port) {
        install(ContentNegotiation) {
            json()
        }
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(15)
            timeout = Duration.ofSeconds(15)
        }

        routing {
            post("/rooms") {
                val createRoomRequest = call.receive<CreateRoomRequest>()
                val name = createRoomRequest.name
                val gameMode = GameModeHolder.modes[createRoomRequest.gameMode]
                if (gameMode == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        RestErrorResponse("Unrecognized game mode ${createRoomRequest.gameMode}")
                    )
                    return@post
                }
                val room = roomManager.createRoom(name, gameMode)
                if (room == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        RestErrorResponse("Room name duplicated")
                    )
                    return@post
                }
                call.respond(HttpStatusCode.Created, CreateRoomResponse(name, gameMode))
            }

            webSocket("/rooms") {
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
