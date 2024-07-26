package com.snspro.edusystem.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
   primary = Color(0xff1D192B),
   secondary = PurpleGrey80,
   tertiary = Pink80,
   onPrimary = Color.White
)

private val LightColorScheme = lightColorScheme(
   primary = Color(0xff1D192B),
   secondary = PurpleGrey40,
   tertiary = Pink40,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),

)

@Composable
fun EduSystemTheme(
   darkTheme: Boolean = isSystemInDarkTheme(),
   content: @Composable () -> Unit
) {
   val colorScheme = LightColorScheme
   val view = LocalView.current
   if (!view.isInEditMode) {
      SideEffect {
         val window = (view.context as Activity).window
         window.statusBarColor = colorScheme.primary.toArgb()
         WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
      }
   }

   MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
   )
}