package net.dyama.whisky.lib.misskey.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
  val id: String,
  val username: String,
  val host: String?,
)
