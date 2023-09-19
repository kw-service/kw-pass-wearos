package dev.yjyoon.kwlibrarywearos.ui.qrcode

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.android.horologist.compose.ambient.AmbientState
import com.google.android.horologist.compose.ambient.AmbientStateUpdate
import dev.yjyoon.kwlibrarywearos.R
import dev.yjyoon.kwlibrarywearos.data.exception.FailedToSignInException
import dev.yjyoon.kwlibrarywearos.ui.component.AlertComponent
import dev.yjyoon.kwlibrarywearos.ui.component.LoadingComponent

@Composable
fun QrCodeScreen(
    viewModel: QrCodeViewModel,
    navigateToAccount: () -> Unit,
    ambientStateUpdate: AmbientStateUpdate,
) {
    val state by viewModel.uiState.collectAsState()

    QrCodeScreen(
        uiState = state,
        navigateToAccount = navigateToAccount,
        refreshQrCode = viewModel::refresh,
        ambientState = ambientStateUpdate.ambientState,
    )
}

@Composable
fun QrCodeScreen(
    uiState: QrCodeUiState,
    navigateToAccount: () -> Unit,
    refreshQrCode: () -> Unit,
    ambientState: AmbientState
) {

    when (uiState) {
        is QrCodeUiState.Success -> {
            QrCodeContent(
                qrcode = uiState.qrcode,
                onRefresh = refreshQrCode,
                onSetting = navigateToAccount,
                ambientState = ambientState
            )
        }

        QrCodeUiState.Loading -> {
            LoadingComponent(textRes = R.string.load_qrcode)
        }

        is QrCodeUiState.Failure -> {
            when (uiState.exception) {
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
                        onAction = refreshQrCode
                    )
                }
            }
        }
    }
}
