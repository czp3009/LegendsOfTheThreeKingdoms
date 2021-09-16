package org.ltk.server.room

import java.util.*
import java.util.concurrent.ConcurrentHashMap

class RoomManager {
    private val rooms = ConcurrentHashMap<UUID, Room>()

    fun createRoom(): Room {
        var room: Room?
        do {
            room = rooms.compute(UUID.randomUUID()) { uuid, existingRoom ->
                if (existingRoom == null) {
                    Room(uuid)
                } else {
                    null
                }
            }
        } while (room == null)
        return room
    }

    fun getRoom(uuid: UUID) = rooms[uuid]

    fun getRooms() = rooms.values.toList()
}
