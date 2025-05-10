package net.dyama.whisky.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeTab(
  modifier: Modifier,
  navController: NavController,
  viewModel: HomeTabViewModel = hiltViewModel(),
) {
  Text("home")
}
