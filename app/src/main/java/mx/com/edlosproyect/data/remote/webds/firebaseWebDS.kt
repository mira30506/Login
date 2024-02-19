package mx.com.edlosproyect.data.remote.webds

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.com.edlosproyect.data.remote.api.ApiFirebase
import mx.com.edlosproyect.sys.utils.NetworkUtils
import mx.com.edlosproyect.sys.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class firebaseWebDS @Inject constructor(private val apiFirebase: ApiFirebase) {

    suspend fun getAuthUser(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            Resource.success(apiFirebase.getAuthUser(email, password))
        } catch (e: Exception) {
            NetworkUtils.handleException(e)
        }
    }
}