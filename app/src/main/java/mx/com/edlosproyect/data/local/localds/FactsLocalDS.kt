package mx.com.edlosproyect.data.local.localds

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.com.edlosproyect.data.local.database.dao.DaoFacts
import mx.com.edlosproyect.data.local.database.entity.Facts
import mx.com.edlosproyect.data.local.mappers.FactsEntityMapper
import mx.com.edlosproyect.data.local.mappers.FactsMapper
import mx.com.edlosproyect.data.local.model.ResultsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FactsLocalDS @Inject constructor(private val daoFacts: DaoFacts) {

    suspend fun getPage(size: Int, page: Int) = withContext(Dispatchers.IO) {
        var result = daoFacts.getPage(size, size * page)
        var list = mutableListOf<ResultsModel>()
        result.forEach {
            list.add(FactsMapper().map(it))
        }
        list
    }

    suspend fun insertFacts(list: List<ResultsModel>) = withContext(Dispatchers.IO) {
        var factsList = mutableListOf<Facts>()
        list.forEach {
            factsList.add(FactsEntityMapper().map(it))
        }
        daoFacts.delete()
        daoFacts.insert(factsList)
    }

    suspend fun getFact(id: String) = withContext(Dispatchers.IO) {
        FactsMapper().map(daoFacts.getInfoResults(id))
    }
}