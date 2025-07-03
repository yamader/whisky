package net.dyama.misskey.entity

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class Note(
  val id: String,
  val createdAt: Instant,
  val text: String?,
  val cw: String?,
  val user: User,
)
