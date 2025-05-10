package net.dyama.whisky.data

import android.content.Context
import androidx.core.net.toUri
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.dyama.whisky.lib.Ulid
import net.dyama.whisky.lib.chomp
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
data class Account(
  val hostUrl: String,
  val username: String,
  val token: String,
) {
  val host = hostUrl.toUri().authority!!
  val id = "@$username@$host"
}

@Singleton
class AccountsRepository @Inject constructor(
  @ApplicationContext private val context: Context,
) {
  private companion object {
    val Context.dataStore by preferencesDataStore("accounts")
  }

  val flow = context.dataStore.data.map { prefs ->
    prefs.asMap()
      .map { it.key.name.chomp("-").second to Json.decodeFromString<Account>(it.value as String) }
      .toMap()
  }

  suspend fun fetch() = flow.firstOrNull() ?: emptyMap()

  suspend fun get(id: String) = fetch()[id]

  suspend fun save(account: Account) = context.dataStore.edit { prefs ->
    val key = prefs.asMap().keys.map { it.name }.find { it.chomp("-").second == account.id }
      ?: (Ulid.generate() + "-" + account.id)
    prefs[stringPreferencesKey(key)] = Json.encodeToString(account)
  }

  suspend fun delete(id: String) = context.dataStore.edit { prefs ->
    prefs.asMap().keys.map { it.name }.find { it.chomp("-").second == id }?.let {
      prefs.remove(stringPreferencesKey(it))
    }
  }
}
