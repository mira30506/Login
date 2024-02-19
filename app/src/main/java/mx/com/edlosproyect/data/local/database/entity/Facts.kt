package mx.com.edlosproyect.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class Facts(
    @PrimaryKey(autoGenerate = true) var uid:Int,
    @ColumnInfo(name="Id")
    var Id: String? = null,
    @ColumnInfo(name ="dateInsert")
    var dateInsert: String? = null,
    @ColumnInfo(name="slug")
    var slug: String? = null,
    @ColumnInfo(name="columns")
    var columns: String? = null,
    @ColumnInfo(name="fact")
    var fact: String? = null,
    @ColumnInfo(name="organization")
    var organization: String? = null,
    @ColumnInfo(name="resource")
    var resource: String? = null,
    @ColumnInfo(name="url")
    var url: String? = null,
    @ColumnInfo(name="operations")
    var operations: String? = null,
    @ColumnInfo(name="dataset")
    var dataset: String? = null,
    @ColumnInfo(name="createdAt")
    var createdAt: Int? = null
) {
    @Ignore
    constructor(Id:String?,slug:String?,dateInsert: String?,columns: String?,fact: String?,organization: String?,resource: String?,url: String?,operations: String?,dataset: String?,createdAt: Int?) : this(
        0,Id,dateInsert,slug,columns,fact,organization,resource,url,operations,dataset, createdAt
    )
}