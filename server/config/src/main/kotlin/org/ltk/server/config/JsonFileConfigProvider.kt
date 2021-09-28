package org.ltk.server.config

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer
import java.io.File
import kotlin.reflect.KType

class JsonFileConfigProvider(filePath: String) : ConfigProvider {
    private val json = Json { prettyPrint = true }
    private val file = File(filePath).absoluteFile
    private var jsonObject: JsonObject

    init {
        if (file.exists()) {
            if (!file.isFile) {
                throw FileAlreadyExistsException(file, reason = "not a file")
            }
            if (!file.canRead() || !file.canWrite()) {
                throw AccessDeniedException(file, reason = "can't read or write")
            }
        } else {
            val parentFile = file.parentFile
            if (parentFile.exists()) {
                if (!parentFile.canRead() || !parentFile.canWrite()) {
                    throw AccessDeniedException(parentFile, reason = "can't read or write")
                }
            } else {
                parentFile.mkdir()
            }
            file.createNewFile()
            file.writeText(json.encodeToString(JsonObject(emptyMap())))
        }
        jsonObject = json.parseToJsonElement(file.readText()).jsonObject
    }

    override fun <T> get(property: String, type: KType): T? =
        jsonObject[property]?.decode(type)

    override fun <T> get(property: String, type: KType, defaultValue: T): T {
        get<T>(property, type)?.let { return it }
        return synchronized(file) {
            get<T>(property, type)?.let { return@let it }
            val newJsonObject = JsonObject(jsonObject.toMutableMap().apply { put(property, defaultValue.encode(type)) })
            jsonObject = newJsonObject
            file.writeText(json.encodeToString(newJsonObject))
            defaultValue
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> JsonElement.decode(type: KType) =
        json.decodeFromJsonElement(json.serializersModule.serializer(type) as KSerializer<T>, this)

    private fun <T> T.encode(type: KType) =
        json.encodeToJsonElement(json.serializersModule.serializer(type), this)
}
