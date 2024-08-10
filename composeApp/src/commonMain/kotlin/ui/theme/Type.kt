package ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

val AppTypography: Typography
    @Composable
    get() = Typography().copy(
        // overline = Typography().overline.copy(color = onSecondaryContainerColor),
    )

val typography: Typography
    @Composable
    get() = AppTypography
