package net.dyama.whisky.lib.misskey.clients

import net.dyama.whisky.lib.misskey.entity.Pong
import net.dyama.whisky.lib.misskey.entity.User

interface BaseClient {
  suspend fun i(): User
  suspend fun ping(): Pong
}
