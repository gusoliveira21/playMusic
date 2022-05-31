package br.com.gusoliveira21.playmusic.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteMusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMusic(favMusic: FavoriteMusicEntity)

    @Query("SELECT * from table_music")
    suspend fun getAllFavMusic() : List<FavoriteMusicEntity>

    //    @Query("SELECT * from" + FavoriteMusicEntity.table_music)
    //    suspend fun getFavoriteMusic() : LiveData<List<FavoriteMusicEntity>>

    //@Query("DELETE FROM" + FavoriteMusicEntity.table_music + "WHERE id = :favMusicId")
    //suspend fun deleteFavoriteMusic(favMusicId: Long)

    //@Delete
    //suspend fun delFavoriteMusic(favMusicId: FavoriteMusicEntity)
}