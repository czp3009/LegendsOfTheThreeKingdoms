package org.ltk.server.room

import org.ltk.server.game.GameMode
import java.util.concurrent.ConcurrentHashMap

class RoomManager {
    private val rooms = ConcurrentHashMap<String, Room>()

    fun createRoom(name: String, gameMode: GameMode) =
        //TODO old element flushed
        rooms.compute(name) { _, existingRoom ->
            if (existingRoom == null) {
                Room(name, gameMode)
            } else {
                null
            }
        }

    fun getRoom(name: String) = rooms[name]

    fun getRooms() = rooms.values.toList()
}
