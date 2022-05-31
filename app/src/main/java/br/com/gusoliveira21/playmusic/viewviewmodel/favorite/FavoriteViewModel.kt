package br.com.gusoliveira21.playmusic.viewviewmodel.favorite

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gusoliveira21.playmusic.model.ModelMusic

class FavoriteViewModel : ViewModel() {
    var seekProgressMusic: SeekBar? = null

    private var mediaPlayer: MediaPlayer? = null

    private var _mutableListOfModelMusic = mutableListOf<ModelMusic>()
    private var _listAlbuns = MutableLiveData<MutableList<ModelMusic>>()
    val listAlbuns: LiveData<MutableList<ModelMusic>>
        get() = _listAlbuns

    var hasContext = false
    val context: LiveData<Context>
        get() = _context
    private var _context = MutableLiveData<Context>()

    private var _hasMusic = MutableLiveData<Boolean>()
    val hasMusic: LiveData<Boolean>
        get() = _hasMusic

    init {
        _hasMusic.value = false
    }

    fun initContext(context: Context) {
        if (hasContext == false) {
            hasContext = true
            _context.value = context
            carregarMusica()
        }
    }


    @SuppressLint("Range")
    fun carregarMusica() {
        //MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val uri =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            else
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION
        )
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

        val cursor: Cursor? = context.value?.contentResolver?.query(
            uri,
            projection,
            selection,
            null,
            sortOrder

        )

        if (cursor != null) {
            while (cursor.moveToNext()) {
                _mutableListOfModelMusic.add(
                    ModelMusic(
                        nameMusic = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)),
                        nameArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        nameAlbum = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                        duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)).toLong(),
                        uri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    )
                )
            }
        }
        _listAlbuns.value = _mutableListOfModelMusic
        cursor?.close()
    }

    fun playMusic(uri: Uri) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context.value, uri)
            mediaPlayer?.start()
            initSeekBar()
        } else if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            mediaPlayer = MediaPlayer.create(context.value, uri)
            mediaPlayer?.start()
            initSeekBar()
        }
    }

    fun buttonPause() {
        if (mediaPlayer!!.isPlaying) {
            mediaPlayer?.pause()
        }
    }

    fun buttonPlay() {
        if ((mediaPlayer != null)) {
            mediaPlayer?.start()
            initSeekBar()
        }
    }

    fun hasMusicSelected() {
        if (_hasMusic.value == false) {
            _hasMusic.value = true
        }
    }

    fun configSeekBar() {
        seekProgressMusic?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer!!.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    fun initSeekBar() {
        configSeekBar()
        seekProgressMusic?.max = mediaPlayer!!.duration
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekProgressMusic?.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    seekProgressMusic?.progress = 0
                }
            }
        }, 0)
    }

    fun onDestroy() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying()) {
            mediaPlayer!!.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            Log.e("teste", "app Destruido")
        }
    }

}