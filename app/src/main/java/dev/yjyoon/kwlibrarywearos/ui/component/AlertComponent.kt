package dev.yjyoon.kwlibrarywearos.ui.component

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.material.CompactButton
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Alert

@Composable
fun AlertComponent(
    @StringRes titleRes: Int,
    icon: ImageVector,
    @StringRes messageRes: Int,
    actionIcon: ImageVector,
    onAction: () -> Unit
) {
    Alert(
        title = { Text(text = stringResource(id = titleRes)) },
        icon = { Icon(imageVector = icon, contentDescription = null) },
        message = { Text(text = stringResource(id = messageRes)) }
    ) {
        item {
            CompactButton(onClick = onAction) {
                Icon(imageVector = actionIcon, contentDescription = null)
            }
        }
    }
}
