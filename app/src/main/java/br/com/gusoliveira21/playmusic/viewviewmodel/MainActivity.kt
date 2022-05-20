package br.com.gusoliveira21.playmusic.viewviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import br.com.gusoliveira21.playmusic.R
import br.com.gusoliveira21.playmusic.databinding.ActivityMainBinding
import br.com.gusoliveira21.playmusic.viewviewmodel.album.AlbumFragment
import br.com.gusoliveira21.playmusic.viewviewmodel.album.AlbumViewModel
import br.com.gusoliveira21.playmusic.viewviewmodel.music.MusicFragment
import androidx.fragment.app.Fragment

/*class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}*/
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val classMusicFrag=MusicFragment()
        val classAlbunFrag=AlbumFragment()

        //setCurrentFragment(classAlbunFrag)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.albuns->setCurrentFragment(classAlbunFrag)
                R.id.favorite->setCurrentFragment(classMusicFrag)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,fragment)
            commit()
        }

}

