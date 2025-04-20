package net.dyama.whisky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import net.dyama.whisky.ui.WhiskyApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private var splash = true

  override fun onCreate(savedInstanceState: Bundle?) {
    val splashScreen = installSplashScreen()
    splashScreen.setKeepOnScreenCondition { splash }

    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      WhiskyApp { splash = false }
    }
  }
}
