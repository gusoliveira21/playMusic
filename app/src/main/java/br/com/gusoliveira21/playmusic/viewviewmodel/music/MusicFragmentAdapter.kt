package br.com.gusoliveira21.playmusic.viewviewmodel.music

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.gusoliveira21.playmusic.R
import br.com.gusoliveira21.playmusic.databinding.ItemMusicaBinding
import br.com.gusoliveira21.playmusic.model.ModelMusica

class MusicFragmentAdapter : RecyclerView.Adapter<MusicFragmentAdapter.ViewHolderMusicas>() {

    var listMusica: MutableList<ModelMusica>? = null
    var context: Context? = null
    lateinit var viewModel: MusicViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMusicas {
        val bindingRecyclerView = ItemMusicaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolderMusicas(bindingRecyclerView)
    }

    override fun onBindViewHolder(viewHolderMusicas: ViewHolderMusicas, position: Int){
        viewHolderMusicas.musica(listMusica!![position])
        viewHolderMusicas.cardMusic.setOnClickListener {
            viewModel.playMusic(listMusica!![position].uri.toUri())
            viewModel.hasMusicSelected()
        }
    }

    override fun getItemCount() = listMusica!!.size

    class ViewHolderMusicas(itemView: ItemMusicaBinding):RecyclerView.ViewHolder(itemView.root) {
        val nomeMusica = itemView.idAdpNomeMusica
        val nomeArtista = itemView.idAdpNomeArtista
        val cardMusic = itemView.cardMusic
        val imagemAlbum = itemView.imgAlbum
        fun musica(musica: ModelMusica) {
            nomeMusica.text = musica.nomeMusica
            nomeArtista.text = musica.nomeArtista
            teste(musica.uri)
        }
        //Como usar o MediaRetriver
        //https://acervolima.com/classe-mediametadataretriever-no-android-com-exemplos/
        //StackOverflow-Como obter imagem de musicas sem capa
        //https://stackoverflow.com/questions/13453678/how-to-get-album-image-using-musicbrainz
        fun teste(uri:String){
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(uri)
            // getting the embedded picture from media
            val art = retriever.embeddedPicture
            if (art != null) {
                // Convert the byte array to a bitmap
                imagemAlbum.setImageBitmap(BitmapFactory.decodeByteArray(art, 0, art.size))
            } else {
                imagemAlbum.setImageResource(R.drawable.ic_musica_36)
            }
            // close object
            retriever.release()
        }
    }
}