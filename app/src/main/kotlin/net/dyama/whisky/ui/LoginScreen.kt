package net.dyama.whisky.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import net.dyama.whisky.R

@Composable
fun LoginScreen(
  navController: NavController,
  viewModel: LoginViewModel = hiltViewModel(),
) {
  val context = LocalContext.current
  var hostOrId by viewModel.hostOrId
  var hostOrIdError by viewModel.hostOrIdError

  Scaffold(
    topBar = {
      CenterAlignedTopAppBar({
        Image(painterResource(R.drawable.ic_splash), contentDescription = null)
      })
    }
  ) { innerPadding ->
    Column(
      Modifier
        .padding(innerPadding)
        .consumeWindowInsets(innerPadding)
        .windowInsetsPadding(WindowInsets.imeAnimationTarget)
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Text(
          "ホストまたはユーザIDを入力してください",
          fontSize = 28.sp,
          fontWeight = FontWeight.Bold,
        )
        Column {
          OutlinedTextField(
            value = hostOrId,
            onValueChange = {
              hostOrId = it
              hostOrIdError = ""
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("ホスト/ユーザID") },
            isError = hostOrIdError.isNotEmpty(),
            singleLine = true,
          )
          if (hostOrIdError.isNotEmpty()) {
            Text(hostOrIdError, color = MaterialTheme.colorScheme.error)
          }
        }
      }
      Spacer(Modifier.weight(1f))
      HorizontalDivider()
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalArrangement = Arrangement.End,
      ) {
        Button(
          onClick = { viewModel.next(context, navController) },
          enabled = hostOrId.isNotEmpty() and hostOrIdError.isEmpty(),
        ) {
          Text("次へ")
        }
      }
    }
  }
}
