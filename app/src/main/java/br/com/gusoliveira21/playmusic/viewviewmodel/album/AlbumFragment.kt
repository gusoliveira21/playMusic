package br.com.gusoliveira21.playmusic.viewviewmodel.album

import android.database.Cursor
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gusoliveira21.playmusic.databinding.AlbumFragmentBinding
import java.io.File


class AlbumFragment : Fragment() {
    private val binding by lazy {AlbumFragmentBinding.inflate(LayoutInflater.from(context))}
    //private val application = requireNotNull(this.activity).application
    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.initContext(requireContext())
    }






}