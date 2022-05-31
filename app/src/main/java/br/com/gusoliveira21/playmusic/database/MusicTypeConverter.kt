package br.com.gusoliveira21.playmusic.database

import androidx.room.TypeConverter
import br.com.gusoliveira21.playmusic.model.ModelMusic
import org.json.JSONObject

class MusicTypeConverter {
    @TypeConverter
    fun fromMusic(musicEntity: ModelMusic):String{
        return JSONObject().apply {
            put("uri", musicEntity.uri)
            put("nameMusic", musicEntity.nameMusic)
            put("nameArtist", musicEntity.nameArtist)
            put("nameAlbum", musicEntity.nameAlbum)
            put("duration", musicEntity.duration)
        }.toString()
    }
    @TypeConverter
    fun toMusic(music: String): ModelMusic{
        val json = JSONObject(music)
        return ModelMusic(
            json.get("uri").toString(),
            json.get("nameMusic").toString(),
            json.get("nameArtist").toString(),
            json.get("nameAlbum").toString(),
            json.get("nameDuration").toString().toLong()
        )
    }
}