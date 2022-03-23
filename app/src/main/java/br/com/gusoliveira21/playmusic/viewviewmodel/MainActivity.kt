package br.com.gusoliveira21.playmusic.viewviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import br.com.gusoliveira21.playmusic.databinding.ActivityMainBinding
import br.com.gusoliveira21.playmusic.viewviewmodel.album.AlbumViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}

