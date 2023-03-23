package dev.yjyoon.kwlibrarywearos.ui.qrcode

sealed class QrCodeUiState {
    data class Success(val qrcode: String) : QrCodeUiState()
    object Loading : QrCodeUiState()
    data class Failure(val exception: Throwable?) : QrCodeUiState()
}
