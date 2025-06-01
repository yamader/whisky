package net.dyama.whisky.lib.misskey.entity

import kotlinx.serialization.Serializable

// cf. https://github.com/misskey-dev/misskey/blob/develop/packages/misskey-js/src/autogen/types.ts

@Serializable
data class User(
  val id: String,
  val name: String?,
  val username: String,
  val host: String?,
  val avatarUrl: String?,
  val avatarBlurhash: String?,
  /*
  val avatarDecorations: List<{
    id: String,
    angle?: Number,
    flipH?: Boolean,
    url: String,
    offsetX?: Number,
    offsetY?: Number,
  }>?,
  */
  val isBot: Boolean?,
  val isCat: Boolean?,
)
