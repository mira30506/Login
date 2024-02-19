package mx.com.edlosproyect.data.local.model


data class ResultsModel(
    var Id: String? = null,
    var dateInsert: String? = null,
    var slug: String? = null,
    var columns: String? = null,
    var fact: String? = null,
    var organization: String? = null,
    var resource: String? = null,
    var url: String? = null,
    var operations: String? = null,
    var dataset: String? = null,
    var createdAt: Int? = null
)