package net.dyama.whisky.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton

enum class AuthSessionType {
  MisskeyMiauth
}

@Serializable
data class AuthSession(
  val id: String,
  val type: AuthSessionType,
  val hostUrl: String,
)

@Singleton
class AuthSessionRepository @Inject constructor(
  @ApplicationContext private val context: Context,
) {
  private companion object {
    val Context.dataStore by preferencesDataStore("auth_sessions")
    val ID = stringPreferencesKey("id")
    val TYPE = stringPreferencesKey("type")
    val HOST_URL = stringPreferencesKey("host_url")
  }

  val flow = context.dataStore.data.mapNotNull {
    runCatching {
      AuthSession(
        id = it[ID]!!,
        type = AuthSessionType.valueOf(it[TYPE]!!),
        hostUrl = it[HOST_URL]!!,
      )
    }.getOrNull()
  }

  suspend fun fetch() = flow.firstOrNull()

  suspend fun save(session: AuthSession) = context.dataStore.edit {
    it[ID] = session.id
    it[TYPE] = session.type.name
    it[HOST_URL] = session.hostUrl
  }

  suspend fun clear() = context.dataStore.edit { it.clear() }
}
