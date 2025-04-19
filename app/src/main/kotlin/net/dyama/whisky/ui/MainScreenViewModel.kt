package net.dyama.whisky.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import net.dyama.whisky.data.AppPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
  private val appPreferencesRepository: AppPreferencesRepository,
) : ViewModel() {
  var currentTab = mutableStateOf(MainScreenTabs.HOME)

  init {
    viewModelScope.launch {
      val prefs = appPreferencesRepository.flow.first()
      currentTab.value = prefs.lastMainScreenTab
    }
  }

  fun setTab(tab: MainScreenTabs) {
    currentTab.value = tab
    viewModelScope.launch {
      appPreferencesRepository.saveMainScreenTab(tab)
    }
  }
}
