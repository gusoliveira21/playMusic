/*
package br.com.gusoliveira21.playmusic.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavoriteMusicEntity::class], version=1)
@TypeConverters(MusicTypeConverter::class)
abstract class FavoriteMusicDataBase: RoomDatabase() {

    abstract fun favoriteMusicDao():FavoriteMusicDao
    companion object{
        private var INSTANCE : FavoriteMusicDataBase?=null
        //TODO: Pesquisar qual a diferença entre usar Synchronized e Volatile
        //TODO: Pesquisar para que serve .fallbackToDestructiveMigration()
        @Synchronized
        fun getInstance(context: Context): FavoriteMusicDataBase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteMusicDataBase::class.java,
                    "database.db").build()
            }
            return INSTANCE as FavoriteMusicDataBase
        }
    }
}*/
