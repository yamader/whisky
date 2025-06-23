package net.dyama.whisky.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
data class CustomEmojiCache(
  val name: String,
  val host: String,
  val url: String,
) {
  val id = "$name@$host"
}

@Singleton
class CustomEmojiCacheRepository @Inject constructor(
  @ApplicationContext private val context: Context,
) {
  private companion object {
    val Context.dataStore by preferencesDataStore("custom_emojis")
  }

  val flow = context.dataStore.data.map { prefs ->
    prefs.asMap()
      .mapKeys { it.key.name }
      .mapValues { Json.decodeFromString<CustomEmojiCache>(it.value as String) }
  }

  suspend fun fetch() = flow.firstOrNull() ?: emptyMap()

  suspend fun save(emoji: CustomEmojiCache) = context.dataStore.edit { prefs ->
    prefs[stringPreferencesKey(emoji.id)] = Json.encodeToString(emoji)
  }

  suspend fun save(emojis: List<CustomEmojiCache>) = context.dataStore.edit { prefs ->
    emojis.forEach { emoji ->
      prefs[stringPreferencesKey(emoji.id)] = Json.encodeToString(emoji)
    }
  }

  suspend fun clear() = context.dataStore.edit { it.clear() }
}
