package br.com.gusoliveira21.playmusic.model


data class ModelMusica (
    var uri: String,
    val nomeMusica: String,
    var nomeArtista: String,
    var nomeAlbum: String,
    var duration: String
){

}