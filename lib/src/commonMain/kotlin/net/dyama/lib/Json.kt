package net.dyama.lib

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

// thanks to: https://github.com/Kotlin/kotlinx.serialization/issues/746#issuecomment-791807112

fun Any?.toJsonElement(): JsonElement = when (this) {
  is Number -> JsonPrimitive(this)
  is Boolean -> JsonPrimitive(this)
  is String -> JsonPrimitive(this)
  is Array<*> -> this.toJsonArray()
  is List<*> -> this.toJsonArray()
  is Map<*, *> -> this.toJsonObject()
  is JsonElement -> this
  else -> JsonNull
}

fun Array<*>.toJsonArray() = JsonArray(map { it.toJsonElement() })
fun Iterable<*>.toJsonArray() = JsonArray(map { it.toJsonElement() })
fun Map<*, *>.toJsonObject() =
  JsonObject(mapKeys { it.key.toString() }.mapValues { it.value.toJsonElement() })

fun jsonObjectOf(vararg pairs: Pair<String, *>) = pairs.toMap().toJsonObject()
