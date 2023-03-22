package dev.yjyoon.kwlibrarywearos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwlibrarywearos.data.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        viewModelScope.launch {
            localRepository.getUserData()
                .onSuccess { _uiState.value = MainUiState.SignedIn(it) }
                .onFailure { _uiState.value = MainUiState.NeedToSignIn }
        }
    }
}
