package net.dyama.whisky.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.dyama.whisky.data.AccountsRepository
import net.dyama.whisky.data.AppPreferencesRepository
import net.dyama.whisky.ui.data.CurrentAccountRepository
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
  private val accountsRepository: AccountsRepository,
  private val appPreferencesRepository: AppPreferencesRepository,
  private val currentAccountRepository: CurrentAccountRepository,
) : ViewModel() {
  var currentTab = mutableStateOf(MainScreenTabs.HOME)

  suspend fun notHaveAnyAccounts() = accountsRepository.fetch().isEmpty()

  init {
    viewModelScope.launch {
      appPreferencesRepository.fetch().let {
        currentTab.value = it.lastMainScreenTab
      }
    }
  }

  fun setAccount(id: String) {
    currentAccountRepository.setCurrentAccountId(id)
  }

  fun setTab(tab: MainScreenTabs) {
    currentTab.value = tab
    viewModelScope.launch {
      appPreferencesRepository.saveLastMainScreenTab(tab)
    }
  }
}
