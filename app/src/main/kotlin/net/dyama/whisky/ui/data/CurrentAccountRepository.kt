package net.dyama.whisky.ui.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.dyama.whisky.data.AccountRepository
import net.dyama.whisky.data.AppPreferencesRepository
import net.dyama.whisky.lib.misskey.MisskeyClientFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentAccountRepository @Inject constructor(
  private val accountRepository: AccountRepository,
  private val appPreferencesRepository: AppPreferencesRepository,
) {
  private val scope = CoroutineScope(Dispatchers.IO)

  private var _currentAccountId = MutableStateFlow<String?>(null)

  val currentAccountFlow = combine(
    accountRepository.flow,
    _currentAccountId.filterNotNull(),
  ) { accounts, id -> accounts[id] }

  val currentClientFlow = currentAccountFlow.filterNotNull().map {
    MisskeyClientFactory.detectClient(it.hostUrl, it.token)
  }.stateIn(scope, SharingStarted.Eagerly, null)

  init {
    scope.launch {
      val accounts = accountRepository.fetch()
      appPreferencesRepository.fetch().lastAccountId.let {
        if (it in accounts) {
          _currentAccountId.value = it
        } else {
          _currentAccountId.value = accounts.keys.firstOrNull()
        }
      }
    }
  }

  fun setCurrentAccountId(id: String) {
    scope.launch {
      if (id in accountRepository.fetch()) {
        _currentAccountId.value = id
        appPreferencesRepository.saveLastAccountId(id)
      }
    }
  }
}
