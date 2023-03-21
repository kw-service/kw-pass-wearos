package dev.yjyoon.kwlibrarywearos.ui.account

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.ui.graphics.vector.ImageVector
import dev.yjyoon.kwlibrarywearos.R

enum class AccountInputType(
    @StringRes val stringResId: Int,
    val icon: ImageVector,
    val inputKey: String
) {
    Id(
        stringResId = R.string.id,
        icon = Icons.Outlined.Badge,
        inputKey = INPUT_KEY_ID
    ),
    Password(
        stringResId = R.string.password,
        icon = Icons.Outlined.Password,
        inputKey = INPUT_KEY_PW
    ),
    Phone(
        stringResId = R.string.phone_number,
        icon = Icons.Outlined.Smartphone,
        inputKey = INPUT_KEY_PHONE
    )
}

val INPUT_KEY_ID = "INPUT_KEY_ID"
val INPUT_KEY_PW = "INPUT_KEY_PW"
val INPUT_KEY_PHONE = "INPUT_KEY_PN"
