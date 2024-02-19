package mx.com.edlosproyect.data.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import mx.com.edlosproyect.data.local.database.dao.DaoFacts
import mx.com.edlosproyect.data.local.database.entity.Facts
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch


@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest {
    private lateinit var database: AppDatabase
    private lateinit var factsDao: DaoFacts

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        factsDao = database.factsDao()
    }

    @After
    fun closeDataBase() {
        database.close()
    }


    @Test
    fun insertFacst() = runBlocking {
        val model = arrayListOf(
            Facts(
                "3", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            )
        )

        val lathch = CountDownLatch(1)
        val insert = async(Dispatchers.IO) {
            factsDao.insert(model)
            lathch.countDown()
        }
        val job = async(Dispatchers.IO) {
            Assert.assertEquals(factsDao.getFacts(), model)
            lathch.countDown()
        }
        lathch.await()
        job.cancelAndJoin()
        insert.cancelAndJoin()
    }


    @Test
    fun deleteElement() = runBlocking {
        val model = arrayListOf(
            Facts(
                "3", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            )
        )

        val lathch = CountDownLatch(1)
        val insert = async(Dispatchers.IO) {
            factsDao.insert(model)
            lathch.countDown()
        }
        val delete = async(Dispatchers.IO) {
            factsDao.delete()
            lathch.countDown()
        }

        val getAll = async(Dispatchers.IO) {
            val result = factsDao.getFacts()
            Assert.assertFalse(result.isNullOrEmpty())
            lathch.countDown()
        }

        lathch.await()
        insert.cancelAndJoin()
        delete.cancelAndJoin()
        getAll.cancelAndJoin()
    }

    @Test
    fun getPagesTest() = runBlocking {
        val model = arrayListOf(
            Facts(
                "1", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ),
            Facts(
                "2", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ),
            Facts(
                "3", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ),
            Facts(
                "4", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ),
            Facts(
                "5", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ),
            Facts(
                "6", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ),
            Facts(
                "7", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ), Facts(
                "8", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ), Facts(
                "9", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ), Facts(
                "10", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ), Facts(
                "11", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ), Facts(
                "12", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ), Facts(
                "3", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ), Facts(
                "3", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            )
        )
        val lathch = CountDownLatch(1)
        val insert = async(Dispatchers.IO) {
            factsDao.insert(model)
            lathch.countDown()
        }
        val getPage = async(Dispatchers.IO) {
            val models = factsDao.getPage(10, 1)
            Assert.assertTrue(models.size == 10)
            lathch.countDown()
        }
        lathch.countDown()
        insert.cancelAndJoin()
        getPage.cancelAndJoin()
    }

    @Test
    fun getInfoResultTest() = runBlocking {
        val model = arrayListOf(
            Facts(
                "1", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            ),
            Facts(
                "2", "32323", "232323", "32323",
                "dfdfd", "dfdfdf", "dfd", "dfdf", "dfdf", "sasa", 34
            )
        )

        val lathch = CountDownLatch(1)
        val insert = async(Dispatchers.IO) {
            factsDao.insert(model)
            lathch.countDown()
        }

        val job= async(Dispatchers.IO) {
            val result = factsDao.getInfoResults("1")
            Assert.assertTrue(result!=null)
            lathch.countDown()
        }
        lathch.countDown()
        insert.cancelAndJoin()
        job.cancelAndJoin()
    }
}