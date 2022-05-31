package br.com.gusoliveira21.playmusic.viewviewmodel.music

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.gusoliveira21.playmusic.R
import br.com.gusoliveira21.playmusic.databinding.ItemMusicaBinding
import br.com.gusoliveira21.playmusic.model.ModelMusic

class MusicFragmentAdapter : RecyclerView.Adapter<MusicFragmentAdapter.ViewHolderMusicas>() {

    var listMusica: MutableList<ModelMusic>? = null
    lateinit var viewModel: MusicViewModel
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMusicas {
        val bindingRecyclerView = ItemMusicaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolderMusicas(bindingRecyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolderMusicas, position: Int){

        holder.configCardMusic(listMusica!![position])
        holder.favoriteMusic()

        holder.btFavorite.setOnClickListener {
            Log.e("teste","favorito clicado")
            viewModel.favoriteHasClicked(listMusica!![position])
        }

        holder.cardMusic.setOnClickListener {
            viewModel.playMusic(listMusica!![position].uri.toUri())
            viewModel.hasMusicSelected()
        }
    }

    override fun getItemCount() = listMusica!!.size

    class ViewHolderMusicas(itemView: ItemMusicaBinding):RecyclerView.ViewHolder(itemView.root) {
        private val nomeMusica = itemView.idAdapterNameMusic
        private val nomeArtista = itemView.idAdpNomeArtista
        //Todo:Adicionar o nome do album no topo da página
        //Todo:Adicionar a duração de cada música na view
        val cardMusic = itemView.cardMusic
        private val imagemAlbum = itemView.imgAlbum
        val btFavorite = itemView.buttonFavorite
        fun favoriteMusic(){

        }
        fun configCardMusic(musica: ModelMusic) {
            nomeMusica.text = musica.nameMusic
            nomeMusica.isSelected = true
            nomeArtista.text = musica.nameArtist
            nomeArtista.isSelected = true
            testeMediaRetriver(musica.uri)
        }
        //Como usar o MediaRetriver
        //https://acervolima.com/classe-mediametadataretriever-no-android-com-exemplos/
        //StackOverflow-Como obter imagem de musicas sem capa
        //https://stackoverflow.com/questions/13453678/how-to-get-album-image-using-musicbrainz
        fun testeMediaRetriver(uri:String){
            /*val retriever = MediaMetadataRetriever()
            retriever.setDataSource(uri)
            val art = retriever.embeddedPicture
            if (art != null) {
                imagemAlbum.setImageBitmap(BitmapFactory.decodeByteArray(art, 0, art.size))
            } else {*/
                imagemAlbum.setImageResource(R.drawable.ic_musica)
            /*}
            retriever.release()*/
        }
    }
}