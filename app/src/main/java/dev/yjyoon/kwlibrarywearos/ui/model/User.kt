package dev.yjyoon.kwlibrarywearos.ui.model

data class User(
    val id: String?,
    val password: String?,
    val phone: String?
) {
    val isValidate = id != null && password != null && phone != null
}
