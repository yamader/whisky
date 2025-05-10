package net.dyama.whisky.lib.misskey.entity

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Note(
  val id: String,
  val createdAt: Instant,
  val text: String?,
  val cw: String?,
  val user: User,
)
