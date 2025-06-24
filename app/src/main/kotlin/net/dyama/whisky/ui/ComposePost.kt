package net.dyama.whisky.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.dyama.whisky.R
import net.dyama.whisky.ui.common.PlainTextField
import net.dyama.whisky.ui.symbols.Symbols
import net.dyama.whisky.ui.symbols.outlined.AccountCircle

@Composable
fun ComposePost(text: String) {
  // val textState = rememberTextFieldState()
  var text by rememberSaveable { mutableStateOf(text) }
  val focusRequester = remember { FocusRequester() }
  val context = LocalContext.current

  fun finish() {
    (context as Activity).finish()
  }

  LaunchedEffect(Unit) {
    // todo: freeFocus on resume
    focusRequester.requestFocus()
  }

  BackHandler {
    finish()
  }

  WhiskyTheme {
    Scaffold(
      topBar = {
        TopAppBar(
          title = {},
          navigationIcon = {
            IconButton(onClick = { finish() }) {
              Icon(Icons.Default.Close, null)
            }
          },
          actions = {
            Button(
              onClick = {},
              modifier = Modifier
                .heightIn(8.dp)
                .padding(horizontal = 8.dp),
              enabled = !text.isEmpty(),
              contentPadding = PaddingValues(horizontal = 16.dp, vertical = 7.dp),
            ) {
              Text(
                context.resources.getString(R.string.do_note),
                fontSize = 16.sp,
              )
            }
          },
        )
      },
    ) { innerPadding ->
      Column(
        Modifier
          .padding(innerPadding)
          .consumeWindowInsets(innerPadding)
          .windowInsetsPadding(WindowInsets.imeAnimationTarget)
      ) {
        Row(
          Modifier
            .weight(1f)
            .verticalScroll(rememberScrollState())
        ) {
          IconButton(
            onClick = {},
            modifier = Modifier.padding(horizontal = 4.dp),
          ) {
            Icon(Symbols.Outlined.AccountCircle, null, Modifier.size(48.dp))
          }
          // todo: visualxformation
          PlainTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
              .weight(1f)
              .focusRequester(focusRequester),
            textStyle = TextStyle(
              fontSize = 20.sp,
              fontWeight = FontWeight.Light,
              lineHeight = 32.sp,
            ),
            placeholder = {
              Text("334", modifier = Modifier.alpha(.5f), fontSize = 20.sp)
            },
            colors = TextFieldDefaults.colors(
              focusedContainerColor = Color.Transparent,
              unfocusedContainerColor = Color.Transparent,
              focusedIndicatorColor = Color.Transparent,
              unfocusedIndicatorColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(
              top = if (text.contains('\n')) 4.dp else 10.dp,
              end = 16.dp,
            ),
          )
          Spacer(Modifier.height(16.dp))
        }
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
          Row {
            //
          }
          Row {
            // todo: toggle
            IconButton(onClick = { }) {
              Icon(Icons.Filled.AddCircle, null)
            }
          }
        }
      }
    }
  }
}
