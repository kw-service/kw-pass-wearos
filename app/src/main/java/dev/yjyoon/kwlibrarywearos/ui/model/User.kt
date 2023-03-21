package dev.yjyoon.kwlibrarywearos.ui.model

import java.io.Serializable

data class User(
    val id: String?,
    val password: String?,
    val phone: String?
) : Serializable {
    val isValidate = id != null && password != null && phone != null
}
