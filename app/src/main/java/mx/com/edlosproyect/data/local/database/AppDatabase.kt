package mx.com.edlosproyect.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.com.edlosproyect.data.local.database.dao.DaoFacts
import mx.com.edlosproyect.data.local.database.entity.Facts


@Database(entities = [Facts::class], version=1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun factsDao(): DaoFacts
}