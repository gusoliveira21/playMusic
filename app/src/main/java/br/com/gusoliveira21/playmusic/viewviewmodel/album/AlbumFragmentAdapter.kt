package br.com.gusoliveira21.playmusic.viewviewmodel.album

import android.R
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.gusoliveira21.playmusic.databinding.ItemAlbumBinding
import br.com.gusoliveira21.playmusic.model.ModelAlbum


class AlbumFragmentAdapter : RecyclerView.Adapter<AlbumFragmentAdapter.ViewHolderAlbum>() {

    var listAlbum: MutableList<ModelAlbum> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAlbum {
        val bindingRecyclerView = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolderAlbum(bindingRecyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolderAlbum, position: Int) {
        holder.putImagem(listAlbum[position])
        holder.putNomeAlbum(listAlbum[position].albumName)
        holder.putNomeArtista(listAlbum[position].albumArtista)

    }

    override fun getItemCount() = listAlbum!!.size

    class ViewHolderAlbum(itemView: ItemAlbumBinding) : RecyclerView.ViewHolder(itemView.root) {
        val viewImagem = itemView.imgAlbum
        val txtNomeAlbum = itemView.idNomeAlbum
        val txtNomeArtista = itemView.idNomeArtista

        fun putNomeAlbum(nomeAlbum:String) = (nomeAlbum.also { txtNomeAlbum.text = it })
        fun putNomeArtista(nomeArtista:String) = (nomeArtista.also { txtNomeArtista.text = it })


        fun putImagem(modelAlbum: ModelAlbum) {
            //val coverPath: String = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
            val img = Drawable.createFromPath(modelAlbum.albumImagem)
            //val coverAlbum: ImageView = //view.findViewById(R.id.album_cover) as ImageView
            viewImagem.setImageDrawable(img)
            /*Glide.with(itemView.rootView.context)
                 .load(modelAlbum.albumImagem)
                 .apply(RequestOptions.bitmapTransform(CropSquareTransformation()))
                 .into(viewImagem)*/
        }
    }
}