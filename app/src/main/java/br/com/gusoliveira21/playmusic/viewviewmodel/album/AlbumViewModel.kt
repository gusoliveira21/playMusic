package br.com.gusoliveira21.playmusic.viewviewmodel.album

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gusoliveira21.playmusic.model.ModelAlbum
import br.com.gusoliveira21.playmusic.model.ModelMusica
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class AlbumViewModel : ViewModel() {

    var hasContext = false
    val context: LiveData<Context>
        get() = _context
    private var _context = MutableLiveData<Context>()

    private var _mutableListOfModelAlbum = mutableListOf<ModelAlbum>()
    private var _listaAlbum = MutableLiveData<MutableList<ModelAlbum>>()
    val listaAlbum: LiveData<MutableList<ModelAlbum>>
        get() = _listaAlbum

    fun initContext(context: Context) {
        if (hasContext == false) {
            hasContext = true
            _context.value = context
            getAlbumsLists()
        }
    }

    @SuppressLint("Range")
    fun getAlbumsLists() {
        viewModelScope.launch {
            coroutineScope {
                val uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
                val albumId = MediaStore.Audio.Albums._ID
                val albumName = MediaStore.Audio.AlbumColumns.ALBUM
                val albumTracks = MediaStore.Audio.Albums.NUMBER_OF_SONGS
                val albumImagem = MediaStore.Audio.Albums.ALBUM_ART
                val albumArtista = MediaStore.Audio.AlbumColumns.ARTIST
                val albumArtstkey = MediaStore.Audio.Artists.ARTIST_KEY
                val columns =
                    arrayOf(albumId,
                        albumName,
                        albumArtista,
                        albumTracks,
                        albumImagem,
                        albumArtstkey)
                val cursor: Cursor? = context.value?.contentResolver?.query(
                    uri,
                    columns,
                    null,
                    null,
                    null)
                if (cursor != null) {
                    for (s in cursor.getColumnNames()) {
                        Log.d("COLUMNS", "Column = $s")
                    }
                }
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        _mutableListOfModelAlbum.add(
                            ModelAlbum(
                                albumArtista = "${
                                    cursor.getString(cursor.getColumnIndex(albumArtista))
                                }",
                                albumName = "${cursor.getString(cursor.getColumnIndex(albumName))}",
                                albumImagem = "${cursor.getString(cursor.getColumnIndex(albumImagem))}",
                                albumId = "${cursor.getString(cursor.getColumnIndex(albumId))}",
                                albumTracks = "${cursor.getString(cursor.getColumnIndex(albumTracks))}",
                                albumArtstkey = "${
                                    cursor.getString(cursor.getColumnIndex(albumArtstkey))
                                }"
                            )
                        )
                    }
                }
                _listaAlbum.value = _mutableListOfModelAlbum
                cursor?.close()
            }
        }
    }


}









