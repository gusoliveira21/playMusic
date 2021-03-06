package br.com.gusoliveira21.playmusic.viewviewmodel.favorite

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gusoliveira21.playmusic.databinding.FavoriteFragmentBinding
import br.com.gusoliveira21.playmusic.model.ModelMusic


class FavoriteFragment : Fragment() {
    //Seekbar
    private var seekProgressMusic: SeekBar? = null
    //View
    private val binding by lazy { FavoriteFragmentBinding.inflate(LayoutInflater.from(context)) }
    private lateinit var listAlbum: MutableList<ModelMusic>
    private lateinit var BarraMediaPlayer: SeekBar
    //Adapter & RecyclerView
    private val adapter = FavoriteFragmentAdapter()
    lateinit var recyclerView: RecyclerView
    //ViewModel
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?,): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configSeekBar()
        listeners()

        configViewModel()
        initRecyclerView()
        if (hasPermission()) {
            observers()
            viewModel.initContext(requireContext())
        }
    }

    private fun listeners(){
        binding.btPlay.setOnClickListener {
            viewModel.buttonPlay()
        }
        binding.btPause.setOnClickListener {
            viewModel.buttonPause()
        }
        binding.btAdvance.setOnClickListener {
            Log.e("teste","Advance clicado")
        }
        binding.btBack.setOnClickListener {
            Log.e("teste","Back clicado")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRecyclerView() {
        recyclerView = binding.idRecyclearViewMusica
    }

    private fun observers() {
        viewModel.listAlbuns.observe(viewLifecycleOwner, Observer { list ->
            listAlbum = list
            adapter(listAlbum)
        })

        viewModel.context.observe(viewLifecycleOwner, Observer {context ->
            Log.e("teste", "chama contexto de novo")
        })

        viewModel.hasMusic.observe(viewLifecycleOwner, Observer { hasMusic ->
            if(hasMusic) binding.idLinearlayoutPlayer.visibility = View.VISIBLE
        })

    }

    private fun adapter(list: MutableList<ModelMusic>) {
        adapter.listMusic = list
        //Todo: Acredito que tenha um tipo de ViewModel que leve com ele o contexto
        adapter.context = context
        adapter.viewModel = viewModel
        viewModel.seekProgressMusic = binding.seekBarMediaPlayer
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun hasPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if ((ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 123)
                if ((ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
                    return hasPermission()
                }
            } else {
                return true
            }
        }
        return false
    }

    private fun configSeekBar() {
        seekProgressMusic = binding.seekBarMediaPlayer
        seekProgressMusic!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Log.e("teste","$progress")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(requireContext(),"-> start tracking",Toast.LENGTH_SHORT).show()
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(requireContext(),"-> stop tracking",Toast.LENGTH_SHORT).show()
            }
        })
    }

}