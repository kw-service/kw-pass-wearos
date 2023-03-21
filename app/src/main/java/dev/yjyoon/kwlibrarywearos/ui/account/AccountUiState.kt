package dev.yjyoon.kwlibrarywearos.ui.account

data class AccountUiState(
    val id: String? = null,
    val password: String? = null,
    val phone: String? = null
) {
    val filled = id != null && password != null && phone != null
}
