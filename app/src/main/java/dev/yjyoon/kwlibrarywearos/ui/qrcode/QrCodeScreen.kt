package dev.yjyoon.kwlibrarywearos.ui.qrcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CompactButton
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import dev.yjyoon.kwlibrarywearos.R
import dev.yjyoon.kwlibrarywearos.data.exception.FailedToSignInException
import dev.yjyoon.kwlibrarywearos.ui.component.LoadingComponent
import dev.yjyoon.kwlibrarywearos.ui.theme.White87

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
                    
                }
                else -> {
                    NetworkError(onFresh = viewModel::refresh)
                }
            }
        }
    }
}

@Composable
fun NetworkError(
    onFresh: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = null,
                tint = White87
            )
            Text(
                text = stringResource(id = R.string.network_error),
                style = MaterialTheme.typography.caption2,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        CompactButton(
            onClick = onFresh,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null
            )
        }
    }
}