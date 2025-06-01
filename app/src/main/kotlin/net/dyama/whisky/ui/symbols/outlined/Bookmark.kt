package net.dyama.whisky.ui.symbols.outlined

// https://composeicons.com/icons/material-symbols/outlined/bookmark

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import net.dyama.whisky.ui.symbols.Symbols

val Symbols.Outlined.Bookmark: ImageVector
  get() {
    if (_bookmark != null) {
      return _bookmark!!
    }
    _bookmark = ImageVector.Builder(
      name = "Outlined.Bookmark",
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
        moveTo(200f, 840f)
        verticalLineToRelative(-640f)
        quadToRelative(0f, -33f, 23.5f, -56.5f)
        reflectiveQuadTo(280f, 120f)
        horizontalLineToRelative(400f)
        quadToRelative(33f, 0f, 56.5f, 23.5f)
        reflectiveQuadTo(760f, 200f)
        verticalLineToRelative(640f)
        lineTo(480f, 720f)
        close()
        moveToRelative(80f, -122f)
        lineToRelative(200f, -86f)
        lineToRelative(200f, 86f)
        verticalLineToRelative(-518f)
        horizontalLineTo(280f)
        close()
        moveToRelative(0f, -518f)
        horizontalLineToRelative(400f)
        close()
      }
    }.build()
    return _bookmark!!
  }

private var _bookmark: ImageVector? = null
