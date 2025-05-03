package net.dyama.whisky

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import net.dyama.whisky.data.Account
import net.dyama.whisky.data.AccountsRepository
import net.dyama.whisky.data.AppPreferencesRepository
import net.dyama.whisky.data.AuthSessionRepository
import net.dyama.whisky.data.AuthSessionType.MisskeyMiauth
import net.dyama.whisky.lib.misskey.MisskeyClientFactory
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
  @Inject lateinit var accountsRepository: AccountsRepository
  @Inject lateinit var appPreferencesRepository: AppPreferencesRepository
  @Inject lateinit var authSessionRepository: AuthSessionRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    val splashScreen = installSplashScreen()
    super.onCreate(savedInstanceState)

    val id = intent.data?.pathSegments?.first() ?: return finish()
    splashScreen.setKeepOnScreenCondition { true }

    lifecycleScope.launch {
      val session = authSessionRepository.fetch() ?: return@launch finish()
      if (session.id != id) return@launch finish()

      val client = HttpClient(CIO) {
        install(ContentNegotiation) { json() }
      }

      when (session.type) {
        MisskeyMiauth -> {
          val res = client.post(session.hostUrl + "/api/miauth/" + session.id + "/check")
          val body = res.body<JsonObject>()
          val ok = body["ok"]?.jsonPrimitive?.booleanOrNull ?: false
          val token = body["token"]?.jsonPrimitive?.contentOrNull

          if (ok && token != null) {
            val client = MisskeyClientFactory.detectClient(session.hostUrl, token)
            val username = client.i().username
            val account = Account(session.hostUrl, username, token)

            accountsRepository.save(account)
            appPreferencesRepository.saveLastAccountId(account.id)
          } else {
            Toast.makeText(
              this@AuthActivity,
              resources.getString(R.string.e_auth_failed),
              Toast.LENGTH_LONG,
            ).show()
          }
        }
      }

      authSessionRepository.clear()

      val intent = Intent(this@AuthActivity, MainActivity::class.java)
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(intent)
      finish()
    }
  }
}
