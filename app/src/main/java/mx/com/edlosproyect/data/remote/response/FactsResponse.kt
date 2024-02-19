package mx.com.edlosproyect.data.remote.response

import com.google.gson.annotations.SerializedName

data class FactsResponse (
    @SerializedName("pagination" ) var pagination : PaginationResponse?        = PaginationResponse(),
    @SerializedName("results"    ) var results    : ArrayList<ResultsResponse> = arrayListOf()
)
data class PaginationResponse(
    @SerializedName("pageSize") var pageSize: Int? = null,
    @SerializedName("page") var page: Int? = null,
    @SerializedName("total") var total: Int? = null
)
data class ResultsResponse (
    @SerializedName("_id"          ) var Id           : String? = null,
    @SerializedName("date_insert"  ) var dateInsert   : String? = null,
    @SerializedName("slug"         ) var slug         : String? = null,
    @SerializedName("columns"      ) var columns      : String? = null,
    @SerializedName("fact"         ) var fact         : String? = null,
    @SerializedName("organization" ) var organization : String? = null,
    @SerializedName("resource"     ) var resource     : String? = null,
    @SerializedName("url"          ) var url          : String? = null,
    @SerializedName("operations"   ) var operations   : String? = null,
    @SerializedName("dataset"      ) var dataset      : String? = null,
    @SerializedName("created_at"   ) var createdAt    : Int?    = null
)