package mx.com.edlosproyect.data.remote.api

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import mx.com.edlosproyect.sys.utils.GlobalConstants
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiFirebase @Inject constructor() {

    suspend fun registerUser(email: String, password: String): Boolean {
        val response = Firebase.auth.createUserWithEmailAndPassword(email, password)
        response.await()
        return response.isSuccessful
    }

    suspend fun getAuthUser(email: String, password: String): Boolean {
        val response = Firebase.auth.signInWithEmailAndPassword(email, password)
        response.await()
        return response.isSuccessful
    }


    fun getAuthGoogleBegin() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(GlobalConstants.ID_SERVER_GOOGLE)
                // Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .build()

}