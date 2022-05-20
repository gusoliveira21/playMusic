package br.com.gusoliveira21.playmusic.viewviewmodel.album

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.ViewGroup
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
        holder.putImage(listAlbum[position].albumImagem)
        holder.putNomeAlbum(listAlbum[position].albumName)
        holder.putNomeArtista(listAlbum[position].albumArtista)

    }

    override fun getItemCount() = listAlbum!!.size

    class ViewHolderAlbum(itemView: ItemAlbumBinding) : RecyclerView.ViewHolder(itemView.root) {
        val viewImagem = itemView.imgAlbum
        val txtNomeAlbum = itemView.idNomeAlbum
        val txtNomeArtista = itemView.idNomeArtista

        fun putNomeAlbum(nomeAlbum: String) = (nomeAlbum.also { txtNomeAlbum.text = it })
        fun putNomeArtista(nomeArtista: String) = (nomeArtista.also { txtNomeArtista.text = it })
        fun putImage(uriImage: String) {
            val img = Drawable.createFromPath(uriImage)
            viewImagem.setImageDrawable(img)
        /*fun teste(){
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(uri)
            // getting the embedded picture from media
            val art = retriever.embeddedPicture
            if (art != null) {
                // Convert the byte array to a bitmap
                imgAlbum.setImageBitmap(BitmapFactory.decodeByteArray(art, 0, art.size))
            } else {
                imgAlbum.setImageResource(R.drawable.no_image)
            }
            // close object
            // close object
            retriever.release()
        }*/
        }
    }
}