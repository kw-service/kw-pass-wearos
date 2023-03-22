package dev.yjyoon.kwlibrarywearos.ui.account

data class AccountUiState(
    val id: String = "",
    val password: String = "",
    val phone: String = ""
) {
    val filled = id.isNotBlank() && password.isNotBlank() && phone.isNotBlank()
}
