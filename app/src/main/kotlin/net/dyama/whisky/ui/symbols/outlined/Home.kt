package net.dyama.whisky.ui.symbols.outlined

// https://composeicons.com/icons/material-symbols/outlined/home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import net.dyama.whisky.ui.symbols.Symbols

val Symbols.Outlined.Home: ImageVector
  get() {
    if (_home != null) {
      return _home!!
    }
    _home = ImageVector.Builder(
      name = "Outlined.Home",
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
        moveTo(240f, 760f)
        horizontalLineToRelative(120f)
        verticalLineToRelative(-240f)
        horizontalLineToRelative(240f)
        verticalLineToRelative(240f)
        horizontalLineToRelative(120f)
        verticalLineToRelative(-360f)
        lineTo(480f, 220f)
        lineTo(240f, 400f)
        close()
        moveToRelative(-80f, 80f)
        verticalLineToRelative(-480f)
        lineToRelative(320f, -240f)
        lineToRelative(320f, 240f)
        verticalLineToRelative(480f)
        horizontalLineTo(520f)
        verticalLineToRelative(-240f)
        horizontalLineToRelative(-80f)
        verticalLineToRelative(240f)
        close()
        moveToRelative(320f, -350f)
      }
    }.build()
    return _home!!
  }

private var _home: ImageVector? = null
