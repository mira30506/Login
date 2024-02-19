package mx.com.edlosproyect.data.remote.api

import mx.com.edlosproyect.data.remote.response.FactsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v1/gobmx.facts")
    suspend fun getValidateMessagesVersion(): Response<FactsResponse>
}