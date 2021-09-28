package org.ltk.server.config

import org.koin.dsl.module

val configModule = module {
    single<ConfigProvider> { JsonFileConfigProvider("./config.json") }
}
