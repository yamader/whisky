package net.dyama.whisky.ui.symbols.filled

// https://composeicons.com/icons/material-symbols/outlined/smart_toy

// todo: fill

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import net.dyama.whisky.ui.symbols.Symbols

val Symbols.Filled.SmartToy: ImageVector
  get() {
    if (_smartToy != null) {
      return _smartToy!!
    }
    _smartToy = ImageVector.Builder(
      name = "Filled.SmartToy",
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
        moveTo(160f, 600f)
        quadToRelative(-50f, 0f, -85f, -35f)
        reflectiveQuadToRelative(-35f, -85f)
        reflectiveQuadToRelative(35f, -85f)
        reflectiveQuadToRelative(85f, -35f)
        verticalLineToRelative(-80f)
        quadToRelative(0f, -33f, 23.5f, -56.5f)
        reflectiveQuadTo(240f, 200f)
        horizontalLineToRelative(120f)
        quadToRelative(0f, -50f, 35f, -85f)
        reflectiveQuadToRelative(85f, -35f)
        reflectiveQuadToRelative(85f, 35f)
        reflectiveQuadToRelative(35f, 85f)
        horizontalLineToRelative(120f)
        quadToRelative(33f, 0f, 56.5f, 23.5f)
        reflectiveQuadTo(800f, 280f)
        verticalLineToRelative(80f)
        quadToRelative(50f, 0f, 85f, 35f)
        reflectiveQuadToRelative(35f, 85f)
        reflectiveQuadToRelative(-35f, 85f)
        reflectiveQuadToRelative(-85f, 35f)
        verticalLineToRelative(160f)
        quadToRelative(0f, 33f, -23.5f, 56.5f)
        reflectiveQuadTo(720f, 840f)
        horizontalLineTo(240f)
        quadToRelative(-33f, 0f, -56.5f, -23.5f)
        reflectiveQuadTo(160f, 760f)
        close()
        moveToRelative(200f, -80f)
        quadToRelative(25f, 0f, 42.5f, -17.5f)
        reflectiveQuadTo(420f, 460f)
        reflectiveQuadToRelative(-17.5f, -42.5f)
        reflectiveQuadTo(360f, 400f)
        reflectiveQuadToRelative(-42.5f, 17.5f)
        reflectiveQuadTo(300f, 460f)
        reflectiveQuadToRelative(17.5f, 42.5f)
        reflectiveQuadTo(360f, 520f)
        moveToRelative(240f, 0f)
        quadToRelative(25f, 0f, 42.5f, -17.5f)
        reflectiveQuadTo(660f, 460f)
        reflectiveQuadToRelative(-17.5f, -42.5f)
        reflectiveQuadTo(600f, 400f)
        reflectiveQuadToRelative(-42.5f, 17.5f)
        reflectiveQuadTo(540f, 460f)
        reflectiveQuadToRelative(17.5f, 42.5f)
        reflectiveQuadTo(600f, 520f)
        moveTo(320f, 680f)
        horizontalLineToRelative(320f)
        verticalLineToRelative(-80f)
        horizontalLineTo(320f)
        close()
        moveToRelative(-80f, 80f)
        horizontalLineToRelative(480f)
        verticalLineToRelative(-480f)
        horizontalLineTo(240f)
        close()
        moveToRelative(240f, -240f)
      }
    }.build()
    return _smartToy!!
  }

private var _smartToy: ImageVector? = null
