package net.dyama.whisky.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import net.dyama.misskey.entity.Note
import net.dyama.whisky.ui.symbols.Symbols
import net.dyama.whisky.ui.symbols.filled.SmartToy
import net.dyama.whisky.ui.symbols.outlined.AccountCircle
import net.dyama.whisky.ui.symbols.outlined.Bookmark
import net.dyama.whisky.ui.symbols.outlined.Favorite
import net.dyama.whisky.ui.symbols.outlined.MoreVert
import net.dyama.whisky.ui.symbols.outlined.Repeat
import net.dyama.whisky.ui.symbols.outlined.Reply
import net.dyama.whisky.ui.symbols.outlined.Share

@Composable
fun Post(note: Note) {
  Column {
    if (false) {
      Row {
        Icon(Symbols.Outlined.Repeat, null)
        Text("リノートしました")
      }
    }

    Row {
      Icon(Symbols.Outlined.AccountCircle, null)

      Column {
        Row {
          Text(note.user.name ?: "", overflow = TextOverflow.Ellipsis)
          Text("@${note.user.username}@${note.user.host}")
          Text("・${note.createdAt}")
          Spacer(Modifier.width(8.dp))
          IconButton(onClick = {}) {
            Icon(Symbols.Outlined.MoreVert, null)
          }
        }
        if (false) {
          Row {
            Icon(Symbols.Filled.SmartToy, null)
            Text("botアカウント")
          }
        }
        if (note.text != null) {
          Text("$note.text")
        }
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
        ) {
          IconButton(onClick = { }) { Icon(Symbols.Outlined.Reply, null) }
          IconButton(onClick = { }) { Icon(Symbols.Outlined.Repeat, null) }
          IconButton(onClick = { }) { Icon(Symbols.Outlined.Favorite, null) }
          Row {
            IconButton(onClick = { }) { Icon(Symbols.Outlined.Bookmark, null) }
            IconButton(onClick = { }) { Icon(Symbols.Outlined.Share, null) }
          }
        }
      }
    }
  }
}
