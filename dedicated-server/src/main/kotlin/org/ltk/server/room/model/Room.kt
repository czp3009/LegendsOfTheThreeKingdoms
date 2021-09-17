package org.ltk.server.room.model

import kotlinx.serialization.Serializable
import org.ltk.server.game.GameMode
import org.ltk.server.game.Role
import org.ltk.server.game.RoleMode

@Serializable
data class CreateRoomRequest(
    val name: String,
    val gameMode: String
)

@Serializable
data class CreateRoomResponse(
    val name: String,
    val gameMode: String,
    val playerCount: Int,
    val rolesDefinition: List<Role>? = null
) {
    constructor(name: String, gameMode: GameMode) : this(
        name,
        gameMode.name,
        gameMode.playerCount,
        if (gameMode is RoleMode) gameMode.rolesDefinition else null
    )
}
