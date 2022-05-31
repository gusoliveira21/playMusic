package br.com.gusoliveira21.playmusic.viewviewmodel.album

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gusoliveira21.playmusic.model.ModelAlbum
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


//TODO: Verificar se há atualizações no armazenamento de mídia
// /*Para acessar arquivos de mídia de maneira mais confiável, principalmente se o app armazenar
// URIs ou dados do armazenamento de mídia em cache, verifique se a versão do armazenamento foi
// modificada desde a última sincronização dos dados de mídia. Para verificar se há atualizações,
// chame getVersion(). A versão retornada é uma string única que muda sempre que o armazenamento de
// mídia é consideravelmente modificado. Se a versão retornada for diferente da última versão
// sincronizada, busque e sincronize novamente o cache de mídia do app.*/
class AlbumViewModel : ViewModel() {
    var hasContext = false
    val context: LiveData<Context>
        get() = _context
    private var _context = MutableLiveData<Context>()

    private var _mutableListOfModelAlbum = mutableListOf<ModelAlbum>()
    private var _listAlbum = MutableLiveData<MutableList<ModelAlbum>>()
    val listAlbum: LiveData<MutableList<ModelAlbum>>
        get() = _listAlbum

    private var _img = MutableLiveData<Bitmap?>()
    val img: LiveData<Bitmap?>
        get() = _img

    fun initContext(context: Context) {
        if (hasContext == false) {
            hasContext = true
            _context.value = context
            getAlbumsLists()
        }
    }

    private fun getAlbumsLists() {
        viewModelScope.launch {
            coroutineScope {
                try {
                    _listAlbum.value = async { getAlbumList() }.await()
                } catch (e: Exception) {
                //TODO: caso não consiga obter os albuns
                }
            }
        }
    }

    @SuppressLint("Range")
    private fun getAlbumList(): MutableList<ModelAlbum> {
        val uri =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Audio.Albums.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI // funciona na versão 26
            }

        val albumName = MediaStore.Audio.AlbumColumns.ALBUM
        val albumArtista = MediaStore.Audio.AlbumColumns.ARTIST
        val albumImagem = MediaStore.Audio.Albums.ALBUM_ART
        val albumId = MediaStore.Audio.Albums._ID
        val albumTracks = MediaStore.Audio.Albums.NUMBER_OF_SONGS
        val albumArtstkey = MediaStore.Audio.Artists.ARTIST_KEY

        val columns = arrayOf(
            albumId,
            albumName,
            albumArtista,
            albumTracks,
            albumImagem,
            albumArtstkey
            )

        context.value?.contentResolver?.query(
            uri,
            columns,
            null,
            null,
            null).use { cursor ->
            while (cursor!!.moveToNext()) {
                _mutableListOfModelAlbum.add(
                    ModelAlbum(
                        albumArtist = "${cursor.getString(cursor.getColumnIndex(albumArtista))}",
                        albumName = "${cursor.getString(cursor.getColumnIndex(albumName))}",
                        albumImage = "${cursor.getString(cursor.getColumnIndex(albumImagem))}",
                        albumId = "${cursor.getString(cursor.getColumnIndex(albumId))}",
                        albumTracks = "${cursor.getString(cursor.getColumnIndex(albumTracks))}",
                        albumArtstkey = "${cursor.getString(cursor.getColumnIndex(albumArtstkey))}"
                    )
                )
            }
            return _mutableListOfModelAlbum
        }
    }
}




