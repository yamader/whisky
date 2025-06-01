package net.dyama.whisky.ui.screens.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import net.dyama.whisky.ui.common.Post

@Composable
fun HomeTab(
  modifier: Modifier,
  navController: NavController,
  viewModel: HomeTabViewModel = hiltViewModel(),
) {
  val refreshing by viewModel.refreshing
  val timeline = viewModel.timeline.collectAsState()

  Column(modifier) {
    Text("HTL")

    PullToRefreshBox(
      isRefreshing = refreshing,
      onRefresh = { viewModel.refresh() },
      modifier = Modifier.fillMaxSize(),
    ) {
      LazyColumn {
        items(timeline.value) { post ->
          Post(post)
        }
      }
    }
  }
}
