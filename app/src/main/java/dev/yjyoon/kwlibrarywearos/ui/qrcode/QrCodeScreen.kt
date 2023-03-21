package dev.yjyoon.kwlibrarywearos.ui.qrcode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.yjyoon.kwlibrarywearos.R
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
            //TODO
        }
    }
}
