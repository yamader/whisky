package net.dyama.whisky.ui

// Generated with: https://material-foundation.github.io/material-theme-builder/?primary=%23FF8C00

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val lightScheme = lightColorScheme(
  primary = Color(0xFF88511E),
  onPrimary = Color(0xFFFFFFFF),
  primaryContainer = Color(0xFFFFDCC3),
  onPrimaryContainer = Color(0xFF6C3A07),
  secondary = Color(0xFF745944),
  onSecondary = Color(0xFFFFFFFF),
  secondaryContainer = Color(0xFFFFDCC3),
  onSecondaryContainer = Color(0xFF5A422E),
  tertiary = Color(0xFF5C6237),
  onTertiary = Color(0xFFFFFFFF),
  tertiaryContainer = Color(0xFFE1E7B0),
  onTertiaryContainer = Color(0xFF454A21),
  error = Color(0xFFBA1A1A),
  onError = Color(0xFFFFFFFF),
  errorContainer = Color(0xFFFFDAD6),
  onErrorContainer = Color(0xFF93000A),
  background = Color(0xFFFFF8F5),
  onBackground = Color(0xFF221A14),
  surface = Color(0xFFFFF8F5),
  onSurface = Color(0xFF221A14),
  surfaceVariant = Color(0xFFF3DFD2),
  onSurfaceVariant = Color(0xFF51443B),
  outline = Color(0xFF847469),
  outlineVariant = Color(0xFFD6C3B6),
  scrim = Color(0xFF000000),
  inverseSurface = Color(0xFF382F28),
  inverseOnSurface = Color(0xFFFEEEE4),
  inversePrimary = Color(0xFFFFB77D),
  surfaceDim = Color(0xFFE7D7CD),
  surfaceBright = Color(0xFFFFF8F5),
  surfaceContainerLowest = Color(0xFFFFFFFF),
  surfaceContainerLow = Color(0xFFFFF1E9),
  surfaceContainer = Color(0xFFFBEBE1),
  surfaceContainerHigh = Color(0xFFF5E5DB),
  surfaceContainerHighest = Color(0xFFEFE0D6),
)

private val darkScheme = darkColorScheme(
  primary = Color(0xFFFFB77D),
  onPrimary = Color(0xFF4D2600),
  primaryContainer = Color(0xFF6C3A07),
  onPrimaryContainer = Color(0xFFFFDCC3),
  secondary = Color(0xFFE3C0A6),
  onSecondary = Color(0xFF422C1A),
  secondaryContainer = Color(0xFF5A422E),
  onSecondaryContainer = Color(0xFFFFDCC3),
  tertiary = Color(0xFFC5CB96),
  onTertiary = Color(0xFF2E330D),
  tertiaryContainer = Color(0xFF454A21),
  onTertiaryContainer = Color(0xFFE1E7B0),
  error = Color(0xFFFFB4AB),
  onError = Color(0xFF690005),
  errorContainer = Color(0xFF93000A),
  onErrorContainer = Color(0xFFFFDAD6),
  background = Color(0xFF19120C),
  onBackground = Color(0xFFEFE0D6),
  surface = Color(0xFF19120C),
  onSurface = Color(0xFFEFE0D6),
  surfaceVariant = Color(0xFF51443B),
  onSurfaceVariant = Color(0xFFD6C3B6),
  outline = Color(0xFF9E8E82),
  outlineVariant = Color(0xFF51443B),
  scrim = Color(0xFF000000),
  inverseSurface = Color(0xFFEFE0D6),
  inverseOnSurface = Color(0xFF382F28),
  inversePrimary = Color(0xFF88511E),
  surfaceDim = Color(0xFF19120C),
  surfaceBright = Color(0xFF413731),
  surfaceContainerLowest = Color(0xFF140D08),
  surfaceContainerLow = Color(0xFF221A14),
  surfaceContainer = Color(0xFF261E18),
  surfaceContainerHigh = Color(0xFF312822),
  surfaceContainerHighest = Color(0xFF3C332C),
)

@Composable
fun AppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> darkScheme
    else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    content = content
  )
}
