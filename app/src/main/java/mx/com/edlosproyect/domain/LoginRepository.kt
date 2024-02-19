package mx.com.edlosproyect.domain

import mx.com.edlosproyect.data.remote.webds.firebaseWebDS
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(private val webDS: firebaseWebDS) {
    suspend fun getAuthUser(email: String, password: String) = webDS.getAuthUser(email, password)
}