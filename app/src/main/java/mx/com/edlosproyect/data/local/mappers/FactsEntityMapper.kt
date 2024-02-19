package mx.com.edlosproyect.data.local.mappers

import mx.com.edlosproyect.data.local.database.entity.Facts
import mx.com.edlosproyect.data.local.model.ResultsModel
import mx.com.edlosproyect.sys.utils.Mapper

class FactsEntityMapper : Mapper<ResultsModel, Facts> {
    override suspend fun map(input: ResultsModel): Facts {
        return Facts(
            Id=input.Id,
            dataset = input.dataset,
            dateInsert = input.dateInsert,
            fact = input.fact,
            operations = input.operations,
            organization = input.organization,
            resource = input.resource,
            createdAt = input.createdAt,
            columns = input.columns,
            slug = input.slug,
            url = input.url
        )
    }

}