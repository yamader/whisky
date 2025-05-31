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
import net.dyama.whisky.lib.jsonObjectOf
import net.dyama.whisky.lib.misskey.entity.Note
import net.dyama.whisky.lib.misskey.entity.Pong
import net.dyama.whisky.lib.misskey.entity.User

open class MisskeyV12Client(
  protected val host: String,
  protected var token: String?,
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
}
