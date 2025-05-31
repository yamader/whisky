package net.dyama.whisky.ui.screens

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import net.dyama.whisky.ui.Routes
import net.dyama.whisky.ui.common.BackHandler
import net.dyama.whisky.ui.screens.mainscreen.ExploreTab
import net.dyama.whisky.ui.screens.mainscreen.HomeTab
import net.dyama.whisky.ui.screens.mainscreen.MessagesTab
import net.dyama.whisky.ui.screens.mainscreen.NotificationsTab

enum class MainScreenTabs {
  HOME, EXPLORE, NOTIFICATIONS, MESSAGES
}

// そのうちブラックリスト→ホワイトリストにしたい
// ModalNavigationDrawerのAnchorを変更する?
fun Modifier.disableHorizontalDrag(): Modifier {
  return pointerInput(Unit) {
    detectHorizontalDragGestures { change, _ -> change.consume() }
  }
}

@Composable
fun NavigationDrawerContent(navController: NavController) {
  ModalDrawerSheet(Modifier.systemBarsPadding()) {
    Box(Modifier.height(64.dp))
    HorizontalDivider(Modifier.padding(32.dp))
    NavigationDrawerItem(
      label = { Text("プロフィール") },
      selected = false,
      onClick = { },
      icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
    )
  }
}

@Composable
fun RowScope.BottomNavButton(
  selected: Boolean,
  onClick: () -> Unit,
  onClickSelected: () -> Unit = onClick,
  icon: ImageVector,
  iconSelected: ImageVector,
) {
  NavigationBarItem(
    selected = selected,
    onClick = if (selected) onClickSelected else onClick,
    icon = {
      Icon(
        if (selected) iconSelected else icon,
        contentDescription = null,
      )
    },
  )
}

@Composable
fun MainScreen(
  navController: NavController,
  onReady: () -> Unit,
  viewModel: MainScreenViewModel = hiltViewModel(),
) {
  //--------------------------------------------------------------
  // どうにかならんものか……
  var render by rememberSaveable { mutableStateOf(false) }
  LaunchedEffect(Unit) {
    if (viewModel.notHaveAnyAccounts()) {
      navController.navigate(Routes.Login) {
        popUpTo(navController.graph.startDestinationId) { inclusive = true }
      }
    } else {
      render = true
    }
    onReady()
  }
  if (!render) return Surface(Modifier.fillMaxSize()) {}
  //--------------------------------------------------------------

  val drawerState = rememberDrawerState(DrawerValue.Closed)
  val scope = rememberCoroutineScope()
  val currentTab by viewModel.currentTab

  BackHandler(currentTab != MainScreenTabs.HOME) {
    viewModel.setTab(MainScreenTabs.HOME)
  }

  ModalNavigationDrawer(
    drawerContent = { NavigationDrawerContent(navController) },
    drawerState = drawerState,
  ) {
    Scaffold(
      topBar = {
        CenterAlignedTopAppBar(
          modifier = Modifier.disableHorizontalDrag(),
          title = { Text("Whisky") },
          navigationIcon = {
            IconButton(onClick = {
              scope.launch { drawerState.open() }
            }) {
              Icon(Icons.Outlined.AccountCircle, contentDescription = null)
            }
          },
          actions = {
            IconButton(onClick = { }) {
              Icon(Icons.Outlined.Settings, contentDescription = null)
            }
          },
        )
      },
      bottomBar = {
        NavigationBar(Modifier.disableHorizontalDrag()) {
          BottomNavButton(
            selected = currentTab == MainScreenTabs.HOME,
            onClick = { viewModel.setTab(MainScreenTabs.HOME) },
            icon = Icons.Outlined.Home,
            iconSelected = Icons.Filled.Home,
          )
          BottomNavButton(
            selected = currentTab == MainScreenTabs.EXPLORE,
            onClick = { viewModel.setTab(MainScreenTabs.EXPLORE) },
            onClickSelected = { },
            icon = Icons.Outlined.Search,
            iconSelected = Icons.Filled.Search,
          )
          BottomNavButton(
            selected = currentTab == MainScreenTabs.NOTIFICATIONS,
            onClick = { viewModel.setTab(MainScreenTabs.NOTIFICATIONS) },
            icon = Icons.Outlined.Notifications,
            iconSelected = Icons.Filled.Notifications,
          )
          BottomNavButton(
            selected = currentTab == MainScreenTabs.MESSAGES,
            onClick = { viewModel.setTab(MainScreenTabs.MESSAGES) },
            icon = Icons.Outlined.Email,
            iconSelected = Icons.Filled.Email,
          )
        }
      },
      floatingActionButton = {
        FloatingActionButton(
          onClick = { },
          modifier = Modifier.disableHorizontalDrag(),
        ) {
          Icon(Icons.Outlined.Create, contentDescription = null)
        }
      },
    ) { innerPadding ->
      val modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)

      when (currentTab) {
        MainScreenTabs.HOME -> HomeTab(modifier, navController)
        MainScreenTabs.EXPLORE -> ExploreTab(modifier, navController)
        MainScreenTabs.NOTIFICATIONS -> NotificationsTab(modifier, navController)
        MainScreenTabs.MESSAGES -> MessagesTab(modifier, navController)
      }
    }
  }
}
