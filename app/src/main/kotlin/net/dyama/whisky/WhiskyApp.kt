package net.dyama.whisky

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun WhiskyApp(modifier: Modifier) {
  NavHost(
    navController = rememberNavController(),
    startDestination = "a",
    modifier = modifier,
  ) {
    composable("a") { Text("hoge") }
    composable("b") { Text("T") }
  }
}
