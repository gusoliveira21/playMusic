package br.com.gusoliveira21.playmusic.viewviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gusoliveira21.playmusic.R
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.gusoliveira21.playmusic.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //adicionando botton navigation: Fiz um processo similar, mas baseado no projeto default da IDE
    //https://www.geeksforgeeks.org/bottom-navigation-bar-in-android/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.favorite_fragment,
            R.id.albuns_fragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}

