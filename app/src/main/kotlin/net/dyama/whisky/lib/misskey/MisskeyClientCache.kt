package net.dyama.whisky.lib.misskey

import net.dyama.whisky.data.CustomEmojiCache

interface MisskeyClientCache {
  val emojis: Map<String, CustomEmojiCache>

  fun saveEmoji(emoji: CustomEmojiCache)
  fun saveEmojis(emojis: List<CustomEmojiCache>)
}
