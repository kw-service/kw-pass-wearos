package dev.yjyoon.kwlibrarywearos.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.yjyoon.kwlibrarywearos.R
import dev.yjyoon.kwlibrarywearos.ui.component.LoadingComponent
import dev.yjyoon.kwlibrarywearos.ui.model.User

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigateToAccount: () -> Unit,
    navigateToQrCode: (User) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is MainUiState.SignedIn -> {
            navigateToQrCode((state as MainUiState.SignedIn).user)
        }
        MainUiState.NeedToSignIn -> {
            navigateToAccount()
        }
        MainUiState.Loading -> {
            LoadingComponent(textRes = R.string.check_account)
        }
    }
}
