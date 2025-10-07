package com.revzion.cognivia.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Simple VM without external DI to keep wiring minimal.
 * Provide your own scope if you want; defaults to Main.
 */
class AuthViewModel(
    private val repo: AuthRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val isLoggedIn: StateFlow<Boolean> =
        repo.isLoggedIn.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun signUp(email: String, password: String, onSuccess: () -> Unit) =
        viewModelScope.launch {
            runCatching {
                _loading.value = true
                repo.signUp(email, password)
            }.onSuccess { onSuccess() }
                .onFailure { _error.value = it.message }
            _loading.value = false
        }

    fun login(email: String, password: String, onSuccess: () -> Unit) =
        viewModelScope.launch {
            runCatching {
                _loading.value = true
                repo.login(email, password)
            }.onSuccess { onSuccess() }
                .onFailure { _error.value = it.message }
            _loading.value = false
        }

    fun loginWithGoogle(idToken: String, onSuccess: () -> Unit) =
        viewModelScope.launch {
            runCatching {
                _loading.value = true
                repo.signInWithGoogleIdToken(idToken)
            }.onSuccess { onSuccess() }
                .onFailure { _error.value = it.message }
            _loading.value = false
        }

    fun signOut() = viewModelScope.launch { repo.signOut() }
}