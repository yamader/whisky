package net.dyama.whisky.ui.common

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun BackHandler(
  enabled: Boolean = true,
  onBack: () -> Unit,
) {
  val currentOnBack by rememberUpdatedState(onBack)
  val lifecycleOwner = LocalActivity.current as? ComponentActivity

  DisposableEffect(enabled, lifecycleOwner) {
    val callback = object : OnBackPressedCallback(enabled) {
      override fun handleOnBackPressed() {
        currentOnBack()
      }
    }

    lifecycleOwner?.onBackPressedDispatcher?.addCallback(callback)
    onDispose { callback.remove() }
  }
}
