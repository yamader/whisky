package net.dyama.whisky.lib

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.head
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import net.dyama.whisky.lib.misskey.MisskeyClientFactory
import java.nio.channels.UnresolvedAddressException

sealed class AuthServers {
  object MisskeyLegacy : AuthServers()
  object MisskeyMiauth : AuthServers()
  object Unknown : AuthServers()
  object Invalid : AuthServers()

  companion object {
    suspend fun detect(host: String): AuthServers {
      val client = HttpClient(CIO) {
        install(ContentNegotiation) { json() }
      }

      try {
        client.head(host)
      } catch (_: UnresolvedAddressException) {
        return Invalid
      }

      // Misskey
      if (
      // もうちょいマシにできんものだろうか……
        client.post("$host/api/endpoint") {
          contentType(ContentType.Application.Json)
          setBody("""{"endpoint":"i"}""")
        }.status.isSuccess()
      ) {
        val ver = MisskeyClientFactory.getVersion(host) ?: return MisskeyLegacy
        return when {
          ver.ge(12, 27) -> MisskeyMiauth
          else -> MisskeyLegacy
        }
      }

      return Unknown
    }
  }
}
