package dev.yjyoon.kwlibrarywearos.ui.main

import dev.yjyoon.kwlibrarywearos.ui.model.User

sealed class MainUiState {
    data class SignedIn(val user: User) : MainUiState()
    object NeedToSignIn : MainUiState()
    object Loading : MainUiState()
}
