package mx.com.edlosproyect.data.remote.webds

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.com.edlosproyect.data.local.mappers.FactsModelMapper
import mx.com.edlosproyect.data.remote.api.ApiService
import mx.com.edlosproyect.sys.utils.NetworkUtils
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FactsWebDS @Inject constructor(private val apiService: ApiService, private val context: Context) {
    suspend fun getFacts() = withContext(Dispatchers.IO) {
        NetworkUtils.genericPetition(context,{
            apiService.getValidateMessagesVersion()
        }){
            FactsModelMapper().map(it.results)
        }
    }
}