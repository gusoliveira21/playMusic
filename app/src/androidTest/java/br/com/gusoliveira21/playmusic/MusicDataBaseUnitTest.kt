package br.com.gusoliveira21.playmusic

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import br.com.gusoliveira21.playmusic.database.FavoriteMusicDao
import br.com.gusoliveira21.playmusic.database.FavoriteMusicDataBase
import br.com.gusoliveira21.playmusic.database.FavoriteMusicEntity
import br.com.gusoliveira21.playmusic.model.ModelMusic
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MusicDataBaseUnitTest {

    private lateinit var favMusicDao: FavoriteMusicDao
    private lateinit var db: FavoriteMusicDataBase

    @Before
    fun createDb(){
        var context =  InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, FavoriteMusicDataBase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(IOException::class)
    fun clodeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun inset(){
        val mockMusic = ModelMusic(
            "456asd456asd456asd",
            "Link Park Mock",
            "Dev rockeiro",
            "No sense",
            "15".toLong())

        var favMusic = FavoriteMusicEntity(1, "mockMusic")
        runBlocking{
            favMusicDao.insertFavMusic(favMusic)
        }

    }

}