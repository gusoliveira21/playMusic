package br.com.gusoliveira21.playmusic.viewviewmodel.album

//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gusoliveira21.playmusic.R
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
    private var _listaAlbum = MutableLiveData<MutableList<ModelAlbum>>()
    val listaAlbum: LiveData<MutableList<ModelAlbum>>
        get() = _listaAlbum

    private var _img = MutableLiveData<Bitmap?>()
    val img: LiveData<Bitmap?>
        get() = _img

    fun initContext(context: Context) {
        if (hasContext == false) {
            hasContext = true
            _context.value = context
            getAlbumsLists()
            //carregarMusica()
            //carregarAlbum()
        }
    }

    private fun getAlbumsLists() {
        viewModelScope.launch {
            coroutineScope {
                try {
                    _listaAlbum.value = async { getAlbumList() }.await()
                } catch (e: Exception) {

                }
            }
        }
    }












    @SuppressLint("Range")
    private fun getAlbumList(): MutableList<ModelAlbum> {
        val uri =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Log.e("Versão 1:"," ${Build.VERSION.SDK_INT}")
                MediaStore.Audio.Albums.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                Log.e("Versão 2:"," ${Build.VERSION.SDK_INT}")
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
            albumArtstkey,
            //MediaStore.Audio.Media.DATA
            //MediaStore.Audio.
            )

        context.value?.contentResolver?.query(
            uri,
            columns,
            null,
            null,
            null).use { cursor ->
            while (cursor!!.moveToNext()) {
                Log.e("teste","--------------------------------------------------------------")
                Log.e("teste","NOME: ${cursor.getString(cursor.getColumnIndex(albumArtista))}")
                Log.e("teste","URI: ${cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))}")//URI da img do album
                //Log.e("teste","URI_MUSICA: ${cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))}")//URI da musica
                Log.e("teste","ID: ${cursor.getString(cursor.getColumnIndex(albumId))}")
                _mutableListOfModelAlbum.add(
                    ModelAlbum(
                        albumArtista = "${cursor.getString(cursor.getColumnIndex(albumArtista))}",
                        albumName = "${cursor.getString(cursor.getColumnIndex(albumName))}",
                        albumImagem = "${cursor.getString(cursor.getColumnIndex(albumImagem))}",
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


/*
        val uri = ContentUris.appendId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.buildUpon(), albumId).build()
        // albumArt is always null
        val albumArt = context.contentResolver.loadThumbnail(uri, Size(640, 640), null)
*/




