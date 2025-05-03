package net.dyama.whisky.ui

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.dyama.whisky.R
import net.dyama.whisky.data.AccountsRepository
import net.dyama.whisky.data.AppPreferencesRepository
import net.dyama.whisky.data.AuthSession
import net.dyama.whisky.data.AuthSessionRepository
import net.dyama.whisky.data.AuthSessionType
import net.dyama.whisky.lib.AuthServers
import net.dyama.whisky.lib.AuthServers.Invalid
import net.dyama.whisky.lib.AuthServers.MisskeyLegacy
import net.dyama.whisky.lib.AuthServers.MisskeyMiauth
import net.dyama.whisky.lib.AuthServers.Unknown
import net.dyama.whisky.lib.Ulid
import net.dyama.whisky.lib.chomp
import net.dyama.whisky.lib.misskey.MisskeyClientFactory
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val accountsRepository: AccountsRepository,
  private val appPreferencesRepository: AppPreferencesRepository,
  private val authSessionRepository: AuthSessionRepository,
) : ViewModel() {
  val hostOrId = mutableStateOf("")
  val hostOrIdError = mutableStateOf("")
  var disableNext = false

  fun host(): String? = hostOrId.value.let {
    when {
      it.startsWith("https://") or it.startsWith("http://") -> it
      it.startsWith('@') and !it.drop(1).contains('@') -> null // e.g. @hoge
      it.contains('@') -> "https://" + it.chomp("@").second
      else -> "https://$it"
    }
  }

  fun next(context: Context, navController: NavController) {
    if (disableNext) return
    disableNext = true
    viewModelScope.launch {
      doLogin(context, navController)
      delay(100) // 連打対策
      disableNext = false
    }
  }

  suspend fun doLogin(context: Context, navController: NavController) {
    val resources = context.resources

    if (accountsRepository.get(hostOrId.value) != null) {
      hostOrIdError.value = resources.getString(R.string.e_acct_already_exists)
      return
    }

    val host = host()!!
    val ulid = Ulid.generate()
    val name = "whisky"
    val icon =
      "https://github.com/yamader/whisky/raw/master/app/src/main/ic_launcher-playstore.png"

    val authUrl = when (AuthServers.detect(host)) {
      MisskeyLegacy -> {
        hostOrIdError.value = resources.getString(R.string.e_wip)
        return
      }

      MisskeyMiauth -> {
        val permissions = MisskeyClientFactory.generatePermissions(host).joinToString(",")
        val callback = "whisky://auth/$ulid"

        authSessionRepository.save(AuthSession(ulid, AuthSessionType.MisskeyMiauth, host))

        "$host/miauth/$ulid?name=$name&icon=$icon&callback=$callback&permission=$permissions"
      }

      Unknown -> {
        hostOrIdError.value = resources.getString(R.string.e_host_server_unknown)
        return
      }

      Invalid -> {
        hostOrIdError.value = resources.getString(R.string.e_host_invalid)
        return
      }
    }

    val customTabIntent = CustomTabsIntent.Builder().build()
    customTabIntent.launchUrl(context, authUrl.toUri())
  }
}
