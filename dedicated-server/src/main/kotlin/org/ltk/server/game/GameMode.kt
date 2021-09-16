package org.ltk.server.game

sealed interface GameMode {
    val playerCount: Int
}

sealed interface RoleMode : GameMode {
    val rolesDefinition: List<Role>
}

class TwoPlayersRoleMode : RoleMode {
    override val playerCount = 2
    override val rolesDefinition = listOf(Role.MONARCH, Role.REBEL)
}

class ThreePlayersRoleMode : RoleMode {
    override val playerCount = 3
    override val rolesDefinition = listOf(Role.MONARCH, Role.REBEL, Role.TRAITOR)
}

class FourPlayersRoleMode : RoleMode {
    override val playerCount = 4
    override val rolesDefinition = listOf(Role.MONARCH, Role.MINISTER, Role.REBEL, Role.TRAITOR)
}

class FivePlayersRoleMode : RoleMode {
    override val playerCount = 5
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER,
        Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

sealed class SixPlayersRoleMode : RoleMode {
    override val playerCount = 6
}

class SixPlayersOneTraitorRoleMode : SixPlayersRoleMode() {
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

class SixPlayersTwoTraitorsRoleMode : SixPlayersRoleMode() {
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER,
        Role.REBEL, Role.REBEL,
        Role.TRAITOR, Role.TRAITOR
    )
}

class SevenPlayersRoleMode : RoleMode {
    override val playerCount = 7
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

sealed class EightPlayersRoleMode : RoleMode {
    override val playerCount = 8
}

class EightPlayersOneTraitorRoleMode : EightPlayersRoleMode() {
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

class EightPlayersTwoTraitorsRoleMode : EightPlayersRoleMode() {
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR, Role.TRAITOR
    )
}

class NinePlayersRoleMode : RoleMode {
    override val playerCount = 9
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

class TenPlayersRoleMode : RoleMode {
    override val playerCount = 10
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR, Role.TRAITOR
    )
}
