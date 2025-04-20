package net.dyama.whisky.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import net.dyama.whisky.data.AccountsRepository
import net.dyama.whisky.data.AppPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
  accountsRepository: AccountsRepository,
  private val appPreferencesRepository: AppPreferencesRepository,
) : ViewModel() {
  var currentAccountId = mutableStateOf<String?>(null)
  var currentTab = mutableStateOf(MainScreenTabs.HOME)

  val accountFlow = combine(
    accountsRepository.flow,
    snapshotFlow { currentAccountId.value }.filterNotNull(),
  ) { accounts, id -> accounts[id] }

  init {
    viewModelScope.launch {
      appPreferencesRepository.fetch().let {
        currentAccountId.value = it.lastAccountId
        currentTab.value = it.lastMainScreenTab
      }
    }
  }

  fun setAccount(id: String) {
    currentAccountId.value = id
    viewModelScope.launch {
      appPreferencesRepository.saveLastAccountId(id)
    }
  }

  fun setTab(tab: MainScreenTabs) {
    currentTab.value = tab
    viewModelScope.launch {
      appPreferencesRepository.saveLastMainScreenTab(tab)
    }
  }
}
