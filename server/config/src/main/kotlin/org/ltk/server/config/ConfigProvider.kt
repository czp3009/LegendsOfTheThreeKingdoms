package org.ltk.server.config

import kotlin.reflect.KType
import kotlin.reflect.typeOf

interface ConfigProvider {
    fun <T> get(property: String, type: KType): T?

    fun <T> get(property: String, type: KType, defaultValue: T): T
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ConfigProvider.get(property: String) = get<T>(property, typeOf<T>())

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> ConfigProvider.get(property: String, defaultValue: T) = get(property, typeOf<T>(), defaultValue)
