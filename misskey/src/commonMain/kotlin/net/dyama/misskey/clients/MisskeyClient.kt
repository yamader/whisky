package net.dyama.misskey.clients

import net.dyama.misskey.entity.Note
import net.dyama.misskey.entity.Pong
import net.dyama.misskey.entity.User

interface MisskeyClient {
  suspend fun ping(): Pong

  // i
  suspend fun i(): User

  // notes
  suspend fun timeline(limit: Int = 10): List<Note>
}
