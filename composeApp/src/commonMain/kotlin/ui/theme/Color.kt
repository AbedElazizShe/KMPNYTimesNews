package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

val onSecondaryContainerColor
    @Composable
    get() = if (isSystemInDarkTheme()) onSecondaryContainerDark
    else onSecondaryContainerLight

val secondaryContainerColor
    @Composable
    get() = if (isSystemInDarkTheme()) secondaryContainerDark
    else secondaryContainerLight

val primaryLight = Color(0xFF8F4C38)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFFFDBD1)
val onPrimaryContainerLight = Color(0xFF3A0B01)
val secondaryLight = Color(0xFF77574E)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFFFDBD1)
val onSecondaryContainerLight = Color(0xFF2C150F)
val tertiaryLight = Color(0xFF6C5D2F)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFF5E1A7)
val onTertiaryContainerLight = Color(0xFF231B00)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFFFF8F6)
val onBackgroundLight = Color(0xFF231917)
val surfaceLight = Color(0xFFFFF8F6)
val onSurfaceLight = Color(0xFF231917)
val surfaceVariantLight = Color(0xFFF5DED8)
val onSurfaceVariantLight = Color(0xFF53433F)
val outlineLight = Color(0xFF85736E)
val outlineVariantLight = Color(0xFFD8C2BC)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF392E2B)
val inverseOnSurfaceLight = Color(0xFFFFEDE8)
val inversePrimaryLight = Color(0xFFFFB5A0)
val surfaceDimLight = Color(0xFFE8D6D2)
val surfaceBrightLight = Color(0xFFFFF8F6)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFFFF1ED)
val surfaceContainerLight = Color(0xFFFCEAE5)
val surfaceContainerHighLight = Color(0xFFF7E4E0)
val surfaceContainerHighestLight = Color(0xFFF1DFDA)

val primaryDark = Color(0xFFFFB5A0)
val onPrimaryDark = Color(0xFF561F0F)
val primaryContainerDark = Color(0xFF723523)
val onPrimaryContainerDark = Color(0xFFFFDBD1)
val secondaryDark = Color(0xFFE7BDB2)
val onSecondaryDark = Color(0xFF442A22)
val secondaryContainerDark = Color(0xFF5D4037)
val onSecondaryContainerDark = Color(0xFFFFDBD1)
val tertiaryDark = Color(0xFFD8C58D)
val onTertiaryDark = Color(0xFF3B2F05)
val tertiaryContainerDark = Color(0xFF534619)
val onTertiaryContainerDark = Color(0xFFF5E1A7)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF1A110F)
val onBackgroundDark = Color(0xFFF1DFDA)
val surfaceDark = Color(0xFF1A110F)
val onSurfaceDark = Color(0xFFF1DFDA)
val surfaceVariantDark = Color(0xFF53433F)
val onSurfaceVariantDark = Color(0xFFD8C2BC)
val outlineDark = Color(0xFFA08C87)
val outlineVariantDark = Color(0xFF53433F)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFF1DFDA)
val inverseOnSurfaceDark = Color(0xFF392E2B)
val inversePrimaryDark = Color(0xFF8F4C38)
val surfaceDimDark = Color(0xFF1A110F)
val surfaceBrightDark = Color(0xFF423734)
val surfaceContainerLowestDark = Color(0xFF140C0A)
val surfaceContainerLowDark = Color(0xFF231917)
val surfaceContainerDark = Color(0xFF271D1B)
val surfaceContainerHighDark = Color(0xFF322825)
val surfaceContainerHighestDark = Color(0xFF3D322F)

val LightAppColors = Colors(
    primary = primaryLight,
    primaryVariant = primaryContainerLight,
    secondary = secondaryLight,
    secondaryVariant = secondaryContainerLight,
    background = backgroundLight,
    surface = surfaceLight,
    error = errorLight,
    onPrimary = onPrimaryLight,
    onSecondary = onSecondaryLight,
    onBackground = onBackgroundLight,
    onSurface = onSurfaceLight,
    onError = onErrorLight,
    isLight = true
)

val DarkAppColors = Colors(
    primary = primaryDark,
    primaryVariant = primaryContainerDark,
    secondary = secondaryDark,
    secondaryVariant = secondaryContainerDark,
    background = backgroundDark,
    surface = surfaceDark,
    error = errorDark,
    onPrimary = onPrimaryDark,
    onSecondary = onSecondaryDark,
    onBackground = onBackgroundDark,
    onSurface = onSurfaceDark,
    onError = onErrorDark,
    isLight = false
)
val lightColors: Colors
    @Composable
    @ReadOnlyComposable
    get() = LightAppColors

val darkColors: Colors
    @Composable
    @ReadOnlyComposable
    get() = DarkAppColors