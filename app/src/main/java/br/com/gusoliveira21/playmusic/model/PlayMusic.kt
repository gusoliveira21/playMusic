package br.com.gusoliveira21.playmusic.model

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.ImageView
import br.com.gusoliveira21.playmusic.viewviewmodel.music.MusicViewModel

class PlayMusic(val context: Context, mediaPlayer: MediaPlayer?) {
    private var viewModel = MusicViewModel()
    private var mediaPlayer = mediaPlayer
    //var mediaPlayer: MediaPlayer? = null
    var imgPlay: ImageView? = null
    var imgPause: ImageView? = null
    var imgAnterior: ImageView? = null
    var imgProxima: ImageView? = null

    fun play(uri: Uri) {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, uri)
        mediaPlayer?.start()
    }

    fun pause() {
        mediaPlayer?.pause()
    }

}
