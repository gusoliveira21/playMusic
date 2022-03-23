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


class AlbumViewModel() : ViewModel() {

    var hasContext = false
    val context: LiveData<Context>
        get() = _context
    private var _context = MutableLiveData<Context>()

    fun initContext(context: Context) {
        if (hasContext == false) {
            hasContext = true
            _context.value = context
            //carregaAlbum()
            getAlbumsLists()
        }
    }

    @SuppressLint("Range")
    fun carregaAlbum() {
        val uriAlbum = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Albums.ALBUM_ART,
            MediaStore.Audio.AlbumColumns.ARTIST,
            MediaStore.Audio.AlbumColumns.ALBUM)
        val selection = MediaStore.Audio.Media._ID + " =?"
        //val sortOrder = "${MediaStore.Audio.AlbumColumns.} DESC"

        val cursor = context.value?.contentResolver?.query(
            uriAlbum,
            projection,
            selection,
            null,
            null
        )//?.use { cursor ->

        if (cursor != null) {
            while (cursor?.moveToNext()!!) {
                Log.e("teste", "================================================")
                Log.e("teste",
                    "${cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))}")
                Log.e("teste",
                    "${cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM))}")
                Log.e("teste",
                    "${cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ARTIST))}")
            }
        }
        cursor?.close()
    }





    @SuppressLint("Range")
    fun getAlbumsLists() {
        val uri: Uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI

        val album_name = MediaStore.Audio.AlbumColumns.ALBUM
        val artist = MediaStore.Audio.AlbumColumns.ARTIST
        val art = MediaStore.Audio.Albums.ALBUM_ART

        val columns = arrayOf(
            MediaStore.Audio.Albums.ALBUM_ART,
            MediaStore.Audio.AlbumColumns.ARTIST,
            MediaStore.Audio.AlbumColumns.ALBUM
        )

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
                Log.e("teste", "================================================")
                Log.e("teste", "${cursor.getString(cursor.getColumnIndex(art))}")
                Log.e("teste", "${cursor.getString(cursor.getColumnIndex(album_name))}")
                Log.e("teste", "${cursor.getString(cursor.getColumnIndex(artist))}")

                var a = cursor.getString(cursor.getColumnIndex(art))
                var b = cursor.getString(cursor.getColumnIndex(album_name))
                var c = cursor.getString(cursor.getColumnIndex(artist))
            }
        }
        cursor?.close()
    }
}









