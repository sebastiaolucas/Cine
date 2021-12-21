package br.com.marquesapps.cine.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import br.com.marquesapps.cine.R

class MainActivity : AppCompatActivity() {

    private val controlador: NavController by lazy{
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controlador.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
        }
    }
}