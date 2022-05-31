package br.com.gusoliveira21.playmusic.viewviewmodel.album

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.gusoliveira21.playmusic.R
import br.com.gusoliveira21.playmusic.databinding.ItemAlbumBinding
import br.com.gusoliveira21.playmusic.model.ModelAlbum

class AlbumFragmentAdapter : RecyclerView.Adapter<AlbumFragmentAdapter.ViewHolderAlbum>() {
    var context: Context? = null
    var listAlbum: MutableList<ModelAlbum> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAlbum {
        val bindingRecyclerView = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolderAlbum(bindingRecyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolderAlbum, position: Int) {
        holder.configCardAlbum(listAlbum[position])

        holder.cardAlbum.setOnClickListener {
            val action = AlbumFragmentDirections.actionAlbunsFragmentToMusicFragment(
                listAlbum[position].albumName
            )
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = listAlbum!!.size

    class ViewHolderAlbum(itemView: ItemAlbumBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val viewImage = itemView.imgAlbum
        private val txtNameAlbum = itemView.idNameAlbum
        private val txtNameArtist = itemView.idNomeArtista
        val cardAlbum = itemView.cardAlbum
        fun configCardAlbum(modelAlbum: ModelAlbum) {
            txtNameAlbum.text = modelAlbum.albumName
            txtNameAlbum.isSelected = true
            txtNameArtist.text = modelAlbum.albumArtist
            txtNameArtist.isSelected = true
            putImage(modelAlbum.albumImage)
        }
        //TODO: Implementar o Glide, para que seja possível editar a forma das imagens
        fun putImage(uriImage: String) {
            val img = Drawable.createFromPath(uriImage)
            if(img == null) viewImage.setImageResource(R.drawable.ic_musica)
            else viewImage.setImageDrawable(img)

        }
    }
}