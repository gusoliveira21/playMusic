/*
package br.com.gusoliveira21.playmusic

//import android.arch.persistence.room.Room
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.gusoliveira21.playmusic.database.FavoriteMusicDao
import br.com.gusoliveira21.playmusic.database.FavoriteMusicDataBase
import br.com.gusoliveira21.playmusic.database.FavoriteMusicEntity
import br.com.gusoliveira21.playmusic.model.ModelMusic
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
*/
/*é um teste unitario, mas escrevi no lugar errado*//*

@RunWith(AndroidJUnit4::class)
class MusicDataBaseTest {
    private lateinit var favMusicDao: FavoriteMusicDao
    private lateinit var db: FavoriteMusicDataBase

    @Before
    fun createDb(){
        var context =  InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, FavoriteMusicDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        favMusicDao = db.favoriteMusicDao()
    }
    @After
    fun clodeDb(){
        db.close()
    }
    @Test
    fun inset(){
        val mockMusic = ModelMusic(
            "456asd456asd456asd",
            "Link Park Mock",
            "Dev rockeiro",
        "No sense",
        "15".toLong())
        //val music = FavoriteMusicEntity(1, mockMusic)
        //favMusicDao.insertFavoriteMusic(music)

        //val fmusic = favMusicDao.getFavoriteMusic()
        //Assert.assertEquals(fmusic, -2)
    }
}
*/
