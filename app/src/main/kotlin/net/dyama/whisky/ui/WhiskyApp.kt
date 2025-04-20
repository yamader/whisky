package net.dyama.whisky.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

object Routes {
  @Serializable object Home
  @Serializable object Login
}

@Composable
fun WhiskyApp(onReady: () -> Unit) {
  val navController = rememberNavController()

  WhiskyTheme {
    NavHost(
      navController = navController,
      startDestination = Routes.Home,
    ) {
      composable<Routes.Home> { MainScreen(navController, onReady) }
      composable<Routes.Login> { LoginScreen(navController) }
    }
  }
}
