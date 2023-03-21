package dev.yjyoon.kwlibrarywearos.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.yjyoon.kwlibrarywearos.R
import dev.yjyoon.kwlibrarywearos.ui.component.LoadingComponent

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigateToAccount: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is MainUiState.SignedIn -> {
            //TODO: QR Code Screen
        }
        MainUiState.NeedToSignIn -> {
            navigateToAccount()
        }
        MainUiState.Loading -> {
            LoadingComponent(textRes = R.string.check_account)
        }
        is MainUiState.Failure -> {
            //TODO
        }
    }
}
