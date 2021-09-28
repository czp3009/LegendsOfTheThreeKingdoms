package org.ltk.server.identity

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.ltk.server.config.ConfigProvider
import org.ltk.server.config.configModule
import org.ltk.server.config.get

fun main() {
    embeddedServer(CIO, port = 8080) {
        install(Koin) {
            modules(configModule)
        }
        install(ContentNegotiation) {
            json()
        }
        routing {
            val configProvider: ConfigProvider by inject()
            get("/api/oauth/settings") {
                call.respond(configProvider.get("oauth", OAuthSettings.DEFAULT))
            }
        }
    }.start(wait = true)
}
