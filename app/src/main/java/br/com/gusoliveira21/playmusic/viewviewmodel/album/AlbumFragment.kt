package br.com.gusoliveira21.playmusic.viewviewmodel.album

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gusoliveira21.playmusic.databinding.AlbumFragmentBinding
import br.com.gusoliveira21.playmusic.model.ModelAlbum

class AlbumFragment : Fragment() {
    private val binding by lazy {AlbumFragmentBinding.inflate(LayoutInflater.from(context))}
    private lateinit var viewModel: AlbumViewModel
    private var adapter = AlbumFragmentAdapter()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configViewModel()
        if (checarPermissao()) {
            observer()
            viewModel.initContext(requireContext())
        }
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun observer() {
        viewModel.listAlbum.observe(viewLifecycleOwner, Observer { listAlbum ->
            configAdapter(listAlbum)
        })
    }

    private fun configAdapter(listAlbum: MutableList<ModelAlbum>) {
        adapter.listAlbum = listAlbum
        adapter.context = context
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun checarPermissao(): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if ((ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 123)
                if ((ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
                    return checarPermissao()
                }
            } else {
                return true
            }
        }
        return false
    }

}





