package net.dyama.whisky.ui.symbols.outlined

// https://composeicons.com/icons/material-symbols/outlined/more_vert

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import net.dyama.whisky.ui.symbols.Symbols

val Symbols.Outlined.MoreVert: ImageVector
  get() {
    if (_moreVert != null) {
      return _moreVert!!
    }
    _moreVert = ImageVector.Builder(
      name = "Outlined.MoreVert",
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
        moveTo(480f, 800f)
        quadToRelative(-33f, 0f, -56.5f, -23.5f)
        reflectiveQuadTo(400f, 720f)
        reflectiveQuadToRelative(23.5f, -56.5f)
        reflectiveQuadTo(480f, 640f)
        reflectiveQuadToRelative(56.5f, 23.5f)
        reflectiveQuadTo(560f, 720f)
        reflectiveQuadToRelative(-23.5f, 56.5f)
        reflectiveQuadTo(480f, 800f)
        moveToRelative(0f, -240f)
        quadToRelative(-33f, 0f, -56.5f, -23.5f)
        reflectiveQuadTo(400f, 480f)
        reflectiveQuadToRelative(23.5f, -56.5f)
        reflectiveQuadTo(480f, 400f)
        reflectiveQuadToRelative(56.5f, 23.5f)
        reflectiveQuadTo(560f, 480f)
        reflectiveQuadToRelative(-23.5f, 56.5f)
        reflectiveQuadTo(480f, 560f)
        moveToRelative(0f, -240f)
        quadToRelative(-33f, 0f, -56.5f, -23.5f)
        reflectiveQuadTo(400f, 240f)
        reflectiveQuadToRelative(23.5f, -56.5f)
        reflectiveQuadTo(480f, 160f)
        reflectiveQuadToRelative(56.5f, 23.5f)
        reflectiveQuadTo(560f, 240f)
        reflectiveQuadToRelative(-23.5f, 56.5f)
        reflectiveQuadTo(480f, 320f)
      }
    }.build()
    return _moreVert!!
  }

private var _moreVert: ImageVector? = null
