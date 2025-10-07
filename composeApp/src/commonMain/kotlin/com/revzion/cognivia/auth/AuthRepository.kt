package com.revzion.cognivia.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Thin wrapper around Firebase Auth (GitLive).
 * Works on Android/iOS from commonMain.
 */
class AuthRepository {
    private val auth = Firebase.auth

    val isLoggedIn: Flow<Boolean> = auth.authStateChanged.map { it != null }

    suspend fun signUp(email: String, password: String): AuthResult =
        auth.createUserWithEmailAndPassword(email, password)

    suspend fun login(email: String, password: String): AuthResult =
        auth.signInWithEmailAndPassword(email, password)

    suspend fun signInWithGoogleIdToken(idToken: String): AuthResult {
        val credential: AuthCredential = GoogleAuthProvider.credential(idToken, null)
        return auth.signInWithCredential(credential)
    }

    suspend fun signOut() = auth.signOut()
}