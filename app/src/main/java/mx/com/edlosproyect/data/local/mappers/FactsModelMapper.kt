package mx.com.edlosproyect.data.local.mappers

import mx.com.edlosproyect.data.local.model.ResultsModel
import mx.com.edlosproyect.data.remote.response.ResultsResponse
import mx.com.edlosproyect.sys.utils.Mapper

class FactsModelMapper : Mapper<ArrayList<ResultsResponse>, ArrayList<ResultsModel>> {
    override suspend fun map(input: ArrayList<ResultsResponse>): ArrayList<ResultsModel> {
       return input.map {
           ResultsModel(
               Id=it.Id,
               dataset = it.dataset,
               dateInsert = it.dateInsert,
               fact = it.fact,
               operations = it.operations,
               organization = it.organization,
               resource = it.resource,
               createdAt = it.createdAt,
               columns = it.columns,
               slug = it.slug,
               url = it.url
           )
       }.toMutableList() as ArrayList<ResultsModel>
    }

}