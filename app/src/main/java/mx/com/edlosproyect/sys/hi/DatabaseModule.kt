package mx.com.edlosproyect.sys.hi

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.com.edlosproyect.data.local.database.AppDatabase

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()


    @Singleton
    @Provides
    fun providesDaoFacst(database: AppDatabase) = database.factsDao()
}