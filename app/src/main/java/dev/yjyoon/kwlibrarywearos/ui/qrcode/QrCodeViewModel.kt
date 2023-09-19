package dev.yjyoon.kwlibrarywearos.ui.qrcode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwlibrarywearos.ui.model.User
import dev.yjyoon.kwlibrarywearos.ui.repository.LocalRepository
import dev.yjyoon.kwlibrarywearos.ui.repository.RemoteRepository
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

    private val user: User = requireNotNull(savedStateHandle.get<User>(EXTRA_KEY_USER))

    private val _uiState = MutableStateFlow<QrCodeUiState>(QrCodeUiState.Loading)
    val uiState: StateFlow<QrCodeUiState> = _uiState

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = QrCodeUiState.Loading
            remoteRepository.getQrCode(user)
                .onSuccess {
                    _uiState.value = QrCodeUiState.Success(it)
                    if (user.autoSignedIn.not()) localRepository.setUserData(user)
                }
                .onFailure {
                    _uiState.value = QrCodeUiState.Failure(it)
                }
        }
    }

    companion object {
        const val EXTRA_KEY_USER = "EXTRA_KEY_USER"
    }
}
