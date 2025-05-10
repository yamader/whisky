package net.dyama.whisky.lib.misskey.clients

import net.dyama.whisky.lib.misskey.entity.Note
import net.dyama.whisky.lib.misskey.entity.Pong
import net.dyama.whisky.lib.misskey.entity.User

interface BaseClient {
  suspend fun ping(): Pong

  // i
  suspend fun i(): User

  // notes
  suspend fun timeline(limit: Int = 10): List<Note>
}
