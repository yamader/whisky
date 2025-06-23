package net.dyama.whisky.data

import net.dyama.whisky.lib.misskey.MisskeyClientCache

class MisskeyClientCacheImpl(
  private val customEmojiCacheRepository: CustomEmojiCacheRepository,
) : MisskeyClientCache {
  val a = mapOf<String, CustomEmojiCache>()

  override val emojis: Map<String, CustomEmojiCache>
    get() = a

  override fun saveEmoji(emoji: CustomEmojiCache) {
    TODO("Not yet implemented")
  }

  override fun saveEmojis(emojis: List<CustomEmojiCache>) {
    TODO("Not yet implemented")
  }
}
