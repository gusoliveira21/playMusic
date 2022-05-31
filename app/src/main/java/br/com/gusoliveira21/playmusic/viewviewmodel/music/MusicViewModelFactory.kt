package br.com.gusoliveira21.playmusic.viewviewmodel.music

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import br.com.gusoliveira21.playmusic.database.FavoriteMusicDao

class MusicViewModelFactory(
        //private val dataSource: FavoriteMusicDao,
        private val application:Application,
        private val albumNameChosen: String
        ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            return MusicViewModel(/*dataSource,*/ application, albumNameChosen) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}