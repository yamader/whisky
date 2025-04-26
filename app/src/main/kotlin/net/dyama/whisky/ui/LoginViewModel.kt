package net.dyama.whisky.ui

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import net.dyama.whisky.data.AccountsRepository
import net.dyama.whisky.data.AppPreferencesRepository
import net.dyama.whisky.lib.misskey.parseId
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  accountsRepository: AccountsRepository,
  private val appPreferencesRepository: AppPreferencesRepository,
) : ViewModel() {
  val hostOrId = mutableStateOf("")

  fun host(): String? = hostOrId.value.let {
    when {
      it.startsWith("https://") or it.startsWith("http://") -> it
      it.contains('@') -> parseId(it).host?.let { host -> "https://$host" }
      else -> "https://$it"
    }
  }

  fun next(context: Context, navController: NavController) {
    CustomTabsIntent.Builder()
      .build()
      .launchUrl(context, host()!!.toUri())
  }
}
