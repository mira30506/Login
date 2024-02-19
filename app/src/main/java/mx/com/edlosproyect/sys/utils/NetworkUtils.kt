package mx.com.edlosproyect.sys.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import retrofit2.Response

class NetworkUtils {
    companion object {
        fun <T : Any, U : Any> handleException(response: Response<T>?): Resource<U> {
            var message = ""
            var code = response?.code().toString()
            when (response?.code()) {
                400 -> {
                    message = "Bas request"
                }

                404 -> {
                    message = "Not found"
                }

                408 -> {
                    message = "Time out"
                }

                else -> {
                    message = "Generic error"
                    code = "31"
                }
            }

            return Resource.error(ErrorHttp(code, message))
        }

        fun <T : Any> handleException(e: Exception): Resource<T> {
            var code = ""
            var message = ""
            when {
                e is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> {
                    code = e.errorCode
                    message = "Credenciales invalidas"
                }

                e is com.google.firebase.FirebaseNetworkException -> {
                    message = "Error de red"
                    code = "31"
                }

                else -> {
                    message = "Generic error"
                    code = "31"
                }
            }
            return Resource.error(ErrorHttp(code, message))
        }

        inline fun <T : Any, R : Any> genericPetition(
            context: Context, genericFunction: () -> Response<T>, mapper: (T) -> R
        ): Resource<R> {
            return try {
                if (isConnected(context)) {
                    val response = genericFunction()
                    if (response.isSuccessful) {
                        Resource.success(response.body()?.let { mapper(it) })
                    } else {
                        handleException(response)
                    }
                }else Resource(Status.ERROR,null, ErrorHttp("31","No hay conexión a internet"))
            } catch (e: Exception) {
                e.printStackTrace()
                handleException(e)
            }
        }

        fun isConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true
                    }
                }
            }
            return false
        }
    }

}


