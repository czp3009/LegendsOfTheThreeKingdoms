package org.ltk.server.room

import java.io.Closeable
import java.util.*

class Room(val uuid: UUID) : Closeable {
    var closed = false
        private set

    override fun close() {
        closed = true
    }
}
