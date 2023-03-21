package dev.yjyoon.kwlibrarywearos.ui.component

import android.app.RemoteInput
import android.content.Intent
import android.view.inputmethod.EditorInfo
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import androidx.wear.input.wearableExtender
import dev.yjyoon.kwlibrarywearos.R
import dev.yjyoon.kwlibrarywearos.ui.account.AccountInputType
import dev.yjyoon.kwlibrarywearos.ui.theme.Primary
import dev.yjyoon.kwlibrarywearos.ui.theme.White87

@Composable
fun KwLibraryChip(
    modifier: Modifier = Modifier,
    type: AccountInputType,
    input: String?,
    onClick: (Intent) -> Unit
) {
    val isValidate = input != null

    val intent: Intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    val remoteInputs: List<RemoteInput> = listOf(
        RemoteInput.Builder(type.inputKey)
            .setLabel(stringResource(type.stringResId))
            .wearableExtender {
                setInputActionType(EditorInfo.IME_ACTION_DONE)
                setEmojisAllowed(false)
            }.build()
    )

    RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)

    Chip(
        label = { Text(stringResource(id = type.stringResId)) },
        secondaryLabel = { Text(input ?: stringResource(id = R.string.unspecified)) },
        onClick = { onClick(intent) },
        icon = {
            Icon(
                imageVector = type.icon,
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