package net.dyama.whisky.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import net.dyama.whisky.ui.screens.MainScreenTabs
import javax.inject.Inject
import javax.inject.Singleton

data class AppPreferences(
  val lastAccountId: String,
  val lastMainScreenTab: MainScreenTabs,
)

@Singleton
class AppPreferencesRepository @Inject constructor(
  @ApplicationContext private val context: Context,
) {
  private companion object {
    val Context.dataStore by preferencesDataStore("app_preferences")
    val LAST_ACCOUNT_ID = stringPreferencesKey("last_account_id")
    val LAST_MAIN_SCREEN_TAB = stringPreferencesKey("last_main_screen_tab")
  }

  val flow = context.dataStore.data.map {
    AppPreferences(
      lastAccountId = it[LAST_ACCOUNT_ID] ?: "",
      lastMainScreenTab = MainScreenTabs.valueOf(
        it[LAST_MAIN_SCREEN_TAB] ?: MainScreenTabs.HOME.name
      )
    )
  }

  suspend fun fetch() = flow.firstOrNull()
    ?: AppPreferences(
      lastAccountId = "",
      lastMainScreenTab = MainScreenTabs.HOME,
    )

  suspend fun saveLastAccountId(id: String) = context.dataStore.edit {
    it[LAST_ACCOUNT_ID] = id
  }

  suspend fun saveLastMainScreenTab(tab: MainScreenTabs) = context.dataStore.edit {
    it[LAST_MAIN_SCREEN_TAB] = tab.name
  }
}
