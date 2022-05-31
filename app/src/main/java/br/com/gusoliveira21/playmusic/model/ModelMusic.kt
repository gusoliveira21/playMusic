package br.com.gusoliveira21.playmusic.model


data class ModelMusic (
    var uri: String,
    val nameMusic: String,
    var nameArtist: String,
    var nameAlbum: String,
    var duration: Long
){

}