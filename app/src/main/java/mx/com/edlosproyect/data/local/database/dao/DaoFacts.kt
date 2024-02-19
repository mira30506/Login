package mx.com.edlosproyect.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mx.com.edlosproyect.data.local.database.entity.Facts


@Dao
interface DaoFacts {
    @Query("SELECT * FROM Facts limit :size Offset :page")
    suspend fun getPage(size: Int, page: Int): List<Facts>

    @Query("Select * from Facts")
    suspend fun getFacts(): Array<Facts>

    @Insert
    suspend fun insert(facts: List<Facts>)

    @Query("delete from Facts")
    suspend fun delete()

    @Query("Select * from Facts where Id=:id")
    suspend fun getInfoResults(id: String): Facts

    @Query("Select * from Facts where organization like :search")
    suspend fun getSearch(search: String): List<Facts>
}