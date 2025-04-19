package net.dyama.whisky.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import net.dyama.whisky.ui.MainScreenTabs
import javax.inject.Inject
import javax.inject.Singleton

data class AppPreferences(
  val lastMainScreenTab: MainScreenTabs,
)

@Singleton
class AppPreferencesRepository @Inject constructor(
  @ApplicationContext private val context: Context,
) {
  private companion object {
    val Context.dataStore by preferencesDataStore("app_preferences")
    val MAIN_SCREEN_TAB = stringPreferencesKey("main_screen_tab")
  }

  val flow = context.dataStore.data.map { preferences ->
    AppPreferences(
      lastMainScreenTab = MainScreenTabs.valueOf(
        preferences[MAIN_SCREEN_TAB] ?: MainScreenTabs.HOME.name
      )
    )
  }

  suspend fun saveMainScreenTab(tab: MainScreenTabs) =
    context.dataStore.edit { preferences -> preferences[MAIN_SCREEN_TAB] = tab.name }
}
