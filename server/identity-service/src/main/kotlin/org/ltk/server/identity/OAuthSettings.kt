package org.ltk.server.identity

import kotlinx.serialization.Serializable

@Serializable
data class OAuthSettings(
    val github: Github
) {
    interface OAuthSetting {
        val enabled: Boolean
    }

    @Serializable
    data class Github(
        override val enabled: Boolean,
        val clientId: String
    ) : OAuthSetting

    companion object {
        val DEFAULT = OAuthSettings(Github(enabled = false, clientId = ""))
    }
}
