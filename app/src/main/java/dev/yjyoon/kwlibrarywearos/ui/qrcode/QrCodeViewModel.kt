package dev.yjyoon.kwlibrarywearos.ui.qrcode

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwlibrarywearos.data.repository.LocalRepository
import dev.yjyoon.kwlibrarywearos.data.repository.RemoteRepository
import dev.yjyoon.kwlibrarywearos.ui.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrCodeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val user: User = savedStateHandle.get<User>(EXTRA_KEY_USER)!!

    private val _uiState = MutableStateFlow<QrCodeUiState>(QrCodeUiState.Loading)
    val uiState: StateFlow<QrCodeUiState> = _uiState

    init {
        viewModelScope.launch {
            remoteRepository.getQrCode(user)
                .onSuccess {
                    _uiState.value = QrCodeUiState.Success(it)
                    if (user.autoSignedIn.not()) localRepository.setUserData(user)
                }
                .onFailure {
                    _uiState.value = QrCodeUiState.Failure(it)
                    Log.e("network error", it.stackTraceToString())
                }
        }
    }

    companion object {
        const val EXTRA_KEY_USER = "EXTRA_KEY_USER"
    }
}
