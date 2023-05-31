package com.example.artelista

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.artelista.databinding.ActivityMenuBinding
import com.example.artelista.model.artista
import com.example.artelista.model.evento
import com.example.artelista.model.galeria
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import org.json.JSONObject

class Activity_menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        configurarNavegacion()

        //--- carga de datos
        val jsonGaleria = JSONArray("[\n" +
                "            {\n" +
                "                'artistagaleria' : 'Henciel Daniel Bulimar',\n" +
                "                'titulogaleria' : 'Memories',\n" +
                "                'preciogaleria' : '1,600',\n" +
                "                'imagengaleria' : 'https://artelista.s3.amazonaws.com/obras/big/3/5/5/1209702-607458d7c0c2a.jpg'\n" +
                "            },\n" +
                "            {\n" +
                "                'artistagaleria' : 'María Berroterán',\n" +
                "                'titulogaleria' : 'Pallace',\n" +
                "                'preciogaleria' : '1,800',\n" +
                "                'imagengaleria' : 'https://artelista.s3.amazonaws.com/obras/big/8/3/9/1201563-602e9746f3abf.jpg'\n" +
                "            },\n" +
                "            {\n" +
                "                'artistagaleria' : 'Marilin López',\n" +
                "                'titulogaleria' : 'Amanecer',\n" +
                "                'preciogaleria' : '1,200',\n" +
                "                'imagengaleria' : 'https://artelista.s3.amazonaws.com/obras/big/3/6/8/1204092-6006ef285e45d.jpg'\n" +
                "            },\n" +
                "            {\n" +
                "                'artistagaleria' : 'German Aguirre',\n" +
                "                'titulogaleria' : 'Atardecer',\n" +
                "                'preciogaleria' : '800',\n" +
                "                'imagengaleria' : 'https://artelista.s3.amazonaws.com/obras/big/3/5/5/1209702-607458d7c0c2a.jpg'\n" +
                "            },\n" +
                "            {\n" +
                "                'artistagaleria' : 'Brenda Martinez',\n" +
                "                'titulogaleria' : 'Hogar',\n" +
                "                'preciogaleria' : '1,100',\n" +
                "                'imagengaleria' : 'https://artelista.s3.amazonaws.com/obras/big/5/5/7/1191748-5f1033f19f2e4.jpg'\n" +
                "            },\n" +
                "            {\n" +
                "                'artistagaleria' : 'Karla Martinez',\n" +
                "                'titulogaleria' : 'Memories',\n" +
                "                'preciogaleria' : '1,600',\n" +
                "                'imagengaleria' : 'https://artelista.s3.amazonaws.com/obras/big/7/2/3/1190859-5ef3a721f3127.jpg'\n" +
                "            }\n" +
                "        ]")

        val jsonArtista = JSONArray("[\n" +
                "            {\n" +
                "                'nombreartista' : 'Alexandra Acudelo',\n" +
                "                'categoriaartista' : 'Oleo',\n" +
                "                'paisartista' : 'Nicaragua'\n" +
                "            },\n" +
                "            {\n" +
                "                'nombreartista' : 'María Berroterán',\n" +
                "                'categoriaartista' : 'Acrilico',\n" +
                "                'paisartista' : 'Colombia'\n" +
                "            },\n" +
                "            {\n" +
                "                'nombreartista' : 'German Aguirre',\n" +
                "                'categoriaartista' : 'Acrilico',\n" +
                "                'paisartista' : 'Peru'\n" +
                "            },\n" +
                "            {\n" +
                "                'nombreartista' : 'Brende Martinez',\n" +
                "                'categoriaartista' : 'Acuarela',\n" +
                "                'paisartista' : 'Costa Rica'\n" +
                "            },\n" +
                "            {\n" +
                "                'nombreartista' : 'Karla Martinez',\n" +
                "                'categoriaartista' : 'Acrilico',\n" +
                "                'paisartista' : 'Panama'\n" +
                "            },\n" +
                "            {\n" +
                "                'nombreartista' : 'Henciel Daniel Bulimar',\n" +
                "                'categoriaartista' : 'Oleo',\n" +
                "                'paisartista' : 'Guatemala'\n" +
                "            }\n" +
                "        ]")

        val jsonEvento = JSONArray("[\n" +
                "            {\n" +
                "                'tituloevento' : 'Armando José Aguirre',\n" +
                "                'categoriaevento' : 'Oleo',\n" +
                "                'horaevento' : '08:00'\n" +
                "            },\n" +
                "            {\n" +
                "                'tituloevento' : 'German Traña Obando',\n" +
                "                'categoriaevento' : 'Acrilico',\n" +
                "                'horaevento' : '10:00'\n" +
                "            },\n" +
                "            {\n" +
                "                'tituloevento' : 'Pol Ledent',\n" +
                "                'categoriaevento' : 'Acrilico',\n" +
                "                'horaevento' : '11:30'\n" +
                "            },\n" +
                "            {\n" +
                "                'tituloevento' : 'Maribel Flores',\n" +
                "                'categoriaevento' : 'Acuarela',\n" +
                "                'horaevento' : '3:00'\n" +
                "            },\n" +
                "            {\n" +
                "                'tituloevento' : 'Nana Tchelidze',\n" +
                "                'categoriaevento' : 'Acrilico',\n" +
                "                'horaevento' : '8:40'\n" +
                "            },\n" +
                "            {\n" +
                "                'tituloevento' : 'Henciel Daniel Bulimar',\n" +
                "                'categoriaevento' : 'Oleo',\n" +
                "                'horaevento' : '10:45'\n" +
                "            }\n" +
                "        ]")

        val fireDB: FirebaseFirestore = FirebaseFirestore.getInstance()
//-----------------------
        for (i in 0 until jsonGaleria.length()) {
            val objGaleria = jsonGaleria.get(i) as JSONObject
            var Galeria = galeria()

            Galeria.artistagaleria = objGaleria.getString("artistagaleria")
            Galeria.preciogaleria = objGaleria.getString("preciogaleria")
            Galeria.imagengaleria = objGaleria.getString("imagengaleria")
            Galeria.titulogaleria = objGaleria.getString("titulogaleria")
            fireDB.collection("Galeria").document().set(Galeria)

        }


        //-----------------------
        for (i in 0 until jsonArtista.length()) {
            val objArtista = jsonArtista.get(i) as JSONObject
            var Artista = artista()

            Artista.nombreartista = objArtista.getString("nombreartista")
            Artista.categoriaartista = objArtista.getString("categoriaartista")
            Artista.paisartista = objArtista.getString("paisartista")

            fireDB.collection("Artista").document().set(Artista)
        }


        //-----------------------
        for (i in 0 until jsonEvento.length()) {
            val objEvento = jsonEvento.get(i) as JSONObject
            var Evento = evento()

            Evento.tituloevento = objEvento.getString("tituloevento")
            Evento.categoriaevento = objEvento.getString("categoriaevento")
            Evento.horaevento = objEvento.getString("horaevento")

            fireDB.collection("Evento").document().set(Evento)
        }


    }

    protected fun configurarNavegacion()
    {
        val bmenu:BottomNavigationView = binding.navMenuArte
        NavigationUI.setupWithNavController(bmenu,Navigation.findNavController(this,R.id.nav_host_fragment_activity_menu))

    }
}