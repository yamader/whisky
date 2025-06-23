package net.dyama.whisky.lib.misskey.clients

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import net.dyama.whisky.data.CustomEmojiCache
import net.dyama.whisky.lib.jsonObjectOf
import net.dyama.whisky.lib.misskey.MisskeyClientCache
import net.dyama.whisky.lib.misskey.entity.Note
import net.dyama.whisky.lib.misskey.entity.Pong
import net.dyama.whisky.lib.misskey.entity.User

open class MisskeyV12Client(
  protected val host: String,
  protected var token: String?,
  protected val cache: MisskeyClientCache?,
) : MisskeyClient {
  protected val client = HttpClient(CIO) {
    install(ContentNegotiation) {
      json(Json { ignoreUnknownKeys = true })
    }
  }

  protected suspend inline fun <reified T> post(
    path: String,
    body: JsonObject = jsonObjectOf(),
  ): T {
    return client.post("$host/api/$path") {
      contentType(ContentType.Application.Json)
      setBody(jsonObjectOf("i" to token) + body)
    }.body()
  }

  override suspend fun ping(): Pong = post("ping")

  override suspend fun i(): User = post("i")

  override suspend fun timeline(limit: Int): List<Note> = post(
    "notes/timeline",
    jsonObjectOf(
      "limit" to limit,
    )
  )

  suspend fun emojis(refresh: Boolean = false): Map<String, String> {
    if (!refresh && cache != null) {
      return cache.emojis.mapValues { it.value.url }
    }

    val res: JsonObject = post("meta")
    val emojisJson = res["emojis"]?.jsonArray ?: return emptyMap()
    val emojis = emojisJson.mapNotNull {
      val name = it.jsonObject["name"]?.jsonPrimitive?.content ?: return@mapNotNull null
      val url = it.jsonObject["url"]?.jsonPrimitive?.content ?: return@mapNotNull null
      val host = it.jsonObject["host"]?.jsonPrimitive?.contentOrNull ?: host
      CustomEmojiCache(name, host, url)
    }
    cache?.saveEmojis(emojis)
    return emojis.associate { "${it.name}@${it.host}" to it.url }
  }

  suspend fun emoji(id: String, refresh: Boolean = false): String? {
    if (cache?.emojis?.get(id) == null) {
    }
    return cache?.emojis?.getOrElse(id) {
      return CustomEmojiCache()
    }?.url
  }
}
