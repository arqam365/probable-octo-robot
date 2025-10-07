package com.revzion.cognivia.auth

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

/**
 * Returns a lambda that starts Google sign-in. Call it from a Button.
 * Pass your Web Client ID from Firebase console.
 */
@Composable
fun rememberGoogleSignInLauncher(
    activity: Activity,
    webClientId: String,
    onToken: (idToken: String) -> Unit,
    onError: (Throwable) -> Unit
): () -> Unit {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { res ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(res.data)
        runCatching { task.result }.onSuccess { acct: GoogleSignInAccount? ->
            val token = acct?.idToken
            if (token != null) onToken(token) else onError(IllegalStateException("No ID token"))
        }.onFailure(onError)
    }

    return remember(activity, webClientId) {
        {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(webClientId)
                .requestEmail()
                .build()
            val client = GoogleSignIn.getClient(activity, gso)
            launcher.launch(client.signInIntent)
        }
    }
}