package mx.com.edlosproyect.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.com.edlosproyect.data.local.database.entity.Facts
import mx.com.edlosproyect.data.local.localds.FactsLocalDS
import mx.com.edlosproyect.data.local.mappers.FactsMapper
import mx.com.edlosproyect.data.local.model.ResultsModel
import mx.com.edlosproyect.data.remote.webds.FactsWebDS
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FactsRepository @Inject constructor(
    private val webDS: FactsWebDS,
    private val localDS: FactsLocalDS
) {
    suspend fun getPage(size: Int, page: Int) = localDS.getPage(size,page)

    suspend fun getFacts() = webDS.getFacts()

    suspend fun insertFacts(list: List<ResultsModel>) =localDS.insertFacts(list)

    suspend fun getFact(id: String) = localDS.getFact(id)
}