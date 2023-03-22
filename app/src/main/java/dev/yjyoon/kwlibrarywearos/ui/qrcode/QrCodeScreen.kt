package dev.yjyoon.kwlibrarywearos.ui.qrcode

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.yjyoon.kwlibrarywearos.R
import dev.yjyoon.kwlibrarywearos.data.exception.FailedToSignInException
import dev.yjyoon.kwlibrarywearos.ui.component.AlertComponent
import dev.yjyoon.kwlibrarywearos.ui.component.LoadingComponent

@Composable
fun QrCodeScreen(
    viewModel: QrCodeViewModel,
    navigateToAccount: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is QrCodeUiState.Success -> {
            QrCodeContent(
                qrcode = (state as QrCodeUiState.Success).qrcode,
                onSetting = navigateToAccount
            )
        }
        QrCodeUiState.Loading -> {
            LoadingComponent(textRes = R.string.load_qrcode)
        }
        is QrCodeUiState.Failure -> {
            when ((state as QrCodeUiState.Failure).exception) {
                FailedToSignInException -> {
                    AlertComponent(
                        titleRes = R.string.signin_error,
                        icon = Icons.Default.Error,
                        messageRes = R.string.input_again,
                        actionIcon = Icons.Default.NavigateNext,
                        onAction = navigateToAccount
                    )
                }
                else -> {
                    AlertComponent(
                        titleRes = R.string.network_error,
                        icon = Icons.Default.WifiOff,
                        messageRes = R.string.check_network,
                        actionIcon = Icons.Default.Refresh,
                        onAction = viewModel::refresh
                    )
                }
            }
        }
    }
}
