package org.ltk.server.game

object GameModeHolder {
    val modes: Map<String, GameMode> = listOf(
        TwoPlayersRoleMode,
        ThreePlayersRoleMode,
        FourPlayersRoleMode,
        FivePlayersRoleMode,
        SixPlayersOneTraitorRoleMode,
        SixPlayersTwoTraitorsRoleMode,
        SevenPlayersRoleMode,
        EightPlayersOneTraitorRoleMode,
        EightPlayersTwoTraitorsRoleMode,
        NinePlayersRoleMode,
        TenPlayersRoleMode
    ).associateBy { it.name }
}

sealed interface GameMode {
    val name: String
    val playerCount: Int
}

sealed interface RoleMode : GameMode {
    override val playerCount get() = rolesDefinition.count()
    val rolesDefinition: List<Role>
}

object TwoPlayersRoleMode : RoleMode {
    override val name = "双人身份模式"
    override val rolesDefinition = listOf(Role.MONARCH, Role.REBEL)
}

object ThreePlayersRoleMode : RoleMode {
    override val name = "三人身份模式"
    override val rolesDefinition = listOf(Role.MONARCH, Role.REBEL, Role.TRAITOR)
}

object FourPlayersRoleMode : RoleMode {
    override val name = "四人身份模式"
    override val rolesDefinition = listOf(Role.MONARCH, Role.MINISTER, Role.REBEL, Role.TRAITOR)
}

object FivePlayersRoleMode : RoleMode {
    override val name = "五人身份模式"
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER,
        Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

sealed class SixPlayersRoleMode : RoleMode

object SixPlayersOneTraitorRoleMode : SixPlayersRoleMode() {
    override val name = "六人身份模式-单内奸"
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

object SixPlayersTwoTraitorsRoleMode : SixPlayersRoleMode() {
    override val name = "六人身份模式-双内奸"
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER,
        Role.REBEL, Role.REBEL,
        Role.TRAITOR, Role.TRAITOR
    )
}

object SevenPlayersRoleMode : RoleMode {
    override val name = "七人身份模式"
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

sealed class EightPlayersRoleMode : RoleMode

object EightPlayersOneTraitorRoleMode : EightPlayersRoleMode() {
    override val name = "八人身份模式-单内奸"
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

object EightPlayersTwoTraitorsRoleMode : EightPlayersRoleMode() {
    override val name = "八人身份模式-双内奸"
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR, Role.TRAITOR
    )
}

object NinePlayersRoleMode : RoleMode {
    override val name = "九人身份模式"
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR
    )
}

object TenPlayersRoleMode : RoleMode {
    override val name = "十人身份模式"
    override val rolesDefinition = listOf(
        Role.MONARCH,
        Role.MINISTER, Role.MINISTER, Role.MINISTER,
        Role.REBEL, Role.REBEL, Role.REBEL, Role.REBEL,
        Role.TRAITOR, Role.TRAITOR
    )
}
