package org.ltk.server.room

import org.ltk.server.game.GameMode
import java.io.Closeable

class Room(val name: String, val gameMode: GameMode) : Closeable {
    var closed = false
        private set

    override fun close() {
        closed = true
    }
}
