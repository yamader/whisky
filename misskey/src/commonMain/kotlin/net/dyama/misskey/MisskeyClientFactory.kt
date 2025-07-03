package net.dyama.misskey

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import net.dyama.lib.Semver
import net.dyama.lib.lt
import net.dyama.lib.major
import net.dyama.lib.toSemver
import net.dyama.misskey.clients.MisskeyClient
import net.dyama.misskey.clients.MisskeyV12Client
import net.dyama.misskey.clients.MisskeyV13Client
import net.dyama.misskey.clients.MisskeyV2023Client
import net.dyama.misskey.clients.MisskeyV2025Client

object MisskeyClientFactory {
  suspend fun getVersion(host: String): Semver? {
    val client = HttpClient(CIO) {
      install(ContentNegotiation) { json() }
    }
    val res = runCatching {
      client.post("$host/api/meta") {
        contentType(ContentType.Application.Json)
        setBody("{}")
      }
    }.getOrElse { return null }
    return res.body<JsonObject>()["version"]?.jsonPrimitive?.contentOrNull?.toSemver()
  }

  suspend fun detectClient(host: String, token: String? = null): MisskeyClient {
    val ver = getVersion(host) ?: return MisskeyV2025Client(host, token)
    return when {
      ver.major <= 12 -> MisskeyV12Client(host, token)
      ver.major == 13 -> MisskeyV13Client(host, token)
      ver.lt(2025, 5) -> MisskeyV2023Client(host, token)
      else -> MisskeyV2025Client(host, token)
    }
  }

  suspend fun generatePermissions(host: String): List<String> {
    val ver = getVersion(host) ?: return MisskeyPermissions.latest
    return when {
      ver.major < 12 -> MisskeyPermissions.ancient
      ver.major == 12 -> MisskeyPermissions.v12
      ver.major == 13 -> MisskeyPermissions.v13
      ver.lt(2025, 5) -> MisskeyPermissions.v2023
      else -> MisskeyPermissions.latest
    }
  }
}
