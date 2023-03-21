package dev.yjyoon.kwlibrarywearos.ui.qrcode

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwlibrarywearos.ui.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrCodeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val user: User = savedStateHandle.get<User>(EXTRA_KEY_USER)!!

    val _uiState = MutableStateFlow<QrCodeUiState>(QrCodeUiState.Loading)
    val uiState: StateFlow<QrCodeUiState> = _uiState

    init {
        viewModelScope.launch {
            //TODO: get QR code through API
            delay(1500L)
            Log.d("user", user.toString())
            _uiState.value = QrCodeUiState.Success("https://blog.yjyoon.dev/")
        }
    }

    companion object {
        const val EXTRA_KEY_USER = "EXTRA_KEY_USER"
    }
}
