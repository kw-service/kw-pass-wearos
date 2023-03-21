package dev.yjyoon.kwlibrarywearos.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.CircularProgressIndicator
import dev.yjyoon.kwlibrarywearos.ui.theme.White87

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
            Loading()
        }
        is MainUiState.Failure -> {
            //TODO
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(indicatorColor = White87)
    }
}
