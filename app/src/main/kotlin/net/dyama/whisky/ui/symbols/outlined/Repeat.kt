package net.dyama.whisky.ui.symbols.outlined

// https://composeicons.com/icons/material-symbols/outlined/repeat

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import net.dyama.whisky.ui.symbols.Symbols

val Symbols.Outlined.Repeat: ImageVector
  get() {
    if (_repeat != null) {
      return _repeat!!
    }
    _repeat = ImageVector.Builder(
      name = "Outlined.Repeat",
      defaultWidth = 24.dp,
      defaultHeight = 24.dp,
      viewportWidth = 960f,
      viewportHeight = 960f
    ).apply {
      path(
        fill = SolidColor(Color.Black),
        fillAlpha = 1.0f,
        stroke = null,
        strokeAlpha = 1.0f,
        strokeLineWidth = 1.0f,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 1.0f,
        pathFillType = PathFillType.NonZero
      ) {
        moveTo(280f, 880f)
        lineTo(120f, 720f)
        lineToRelative(160f, -160f)
        lineToRelative(56f, 58f)
        lineToRelative(-62f, 62f)
        horizontalLineToRelative(406f)
        verticalLineToRelative(-160f)
        horizontalLineToRelative(80f)
        verticalLineToRelative(240f)
        horizontalLineTo(274f)
        lineToRelative(62f, 62f)
        close()
        moveToRelative(-80f, -440f)
        verticalLineToRelative(-240f)
        horizontalLineToRelative(486f)
        lineToRelative(-62f, -62f)
        lineToRelative(56f, -58f)
        lineToRelative(160f, 160f)
        lineToRelative(-160f, 160f)
        lineToRelative(-56f, -58f)
        lineToRelative(62f, -62f)
        horizontalLineTo(280f)
        verticalLineToRelative(160f)
        close()
      }
    }.build()
    return _repeat!!
  }

private var _repeat: ImageVector? = null
