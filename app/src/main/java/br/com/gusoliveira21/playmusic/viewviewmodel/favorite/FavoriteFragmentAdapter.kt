package br.com.gusoliveira21.playmusic.viewviewmodel.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.gusoliveira21.playmusic.R
import br.com.gusoliveira21.playmusic.databinding.ItemMusicaBinding
import br.com.gusoliveira21.playmusic.model.ModelMusic

class FavoriteFragmentAdapter : RecyclerView.Adapter<FavoriteFragmentAdapter.ViewHolderMusic>() {

    var listMusic: MutableList<ModelMusic>? = null
    var context: Context? = null
    lateinit var viewModel: FavoriteViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMusic {
        val bindingRecyclerView = ItemMusicaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolderMusic(bindingRecyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolderMusic, position: Int){
        holder.configCardMusicFavorite(listMusic!![position])

        holder.cardMusic.setOnClickListener {
            viewModel.playMusic(listMusic!![position].uri.toUri())
            viewModel.hasMusicSelected()
        }
    }

    override fun getItemCount() = listMusic!!.size

    class ViewHolderMusic(itemView: ItemMusicaBinding):RecyclerView.ViewHolder(itemView.root) {
        private val nameMusic = itemView.idAdapterNameMusic
        private val nameArtist = itemView.idAdpNomeArtista
        private val imageAlbum = itemView.imgAlbum
        val cardMusic = itemView.cardMusic
        fun configCardMusicFavorite(music: ModelMusic) {
            nameMusic.text = music.nameMusic
            nameMusic.isSelected = true
            nameArtist.text = music.nameArtist
            nameArtist.isSelected = true
            mediaRetriver(music.uri)
        }

        fun mediaRetriver(uri:String){
            /*val retriever = MediaMetadataRetriever()
            retriever.setDataSource(uri)
            val art = retriever.embeddedPicture
            if (art != null) {
                imagemAlbum.setImageBitmap(BitmapFactory.decodeByteArray(art, 0, art.size))
            } else {*/
                imageAlbum.setImageResource(R.drawable.ic_musica)
            /*}
            retriever.release()*/
        }
    }
}