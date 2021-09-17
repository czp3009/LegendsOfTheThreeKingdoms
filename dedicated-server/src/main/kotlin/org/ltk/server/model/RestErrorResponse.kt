package org.ltk.server.model

import kotlinx.serialization.Serializable

@Serializable
data class RestErrorResponse(
    val message: String
) {
    val error = true
}
