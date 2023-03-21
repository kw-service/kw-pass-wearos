package dev.yjyoon.kwlibrarywearos.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import dev.yjyoon.kwlibrarywearos.R
import dev.yjyoon.kwlibrarywearos.ui.theme.Primary
import dev.yjyoon.kwlibrarywearos.ui.theme.White87

@Composable
fun KwLibraryChip(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    secondaryLabel: String?,
    onClick: () -> Unit
) {
    val isValidate = secondaryLabel != null

    Chip(
        label = { Text(label) },
        secondaryLabel = { Text(secondaryLabel ?: stringResource(id = R.string.unspecified)) },
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        colors = ChipDefaults.chipColors(
            backgroundColor = if (isValidate) Primary else Color.Transparent,
            contentColor = White87
        ),
        border = ChipDefaults.chipBorder(
            borderStroke = BorderStroke(
                width = if (isValidate) 0.dp else 1.dp,
                color = if (isValidate) Color.Transparent else White87
            )
        ),
        modifier = modifier
    )
}