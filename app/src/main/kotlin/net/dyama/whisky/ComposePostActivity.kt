package net.dyama.whisky

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import net.dyama.whisky.ui.ComposePost

class ComposePostActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val text = when (intent?.action) {
      Intent.ACTION_SEND -> {
        if (intent.type == "text/plain") {
          intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
        } else {
          ""
        }
      }

      else -> ""
    }

    setContent {
      ComposePost(text = text)
    }
  }
}
