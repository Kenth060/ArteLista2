package com.example.artelista.ui.artista
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artelista.R
import com.example.artelista.adapter.adapterartista
import com.example.artelista.adapter.adapterevento
import com.example.artelista.adapter.artistaListener
import com.example.artelista.databinding.FragmentArtistaBinding
import com.example.artelista.model.artista
import com.example.artelista.model.galeria
import com.example.artelista.viewmodel.ArtistaViewModel


class ArtistaFragment : Fragment(), artistaListener {

    private var fbinding: FragmentArtistaBinding? = null
    private val binding get() = fbinding!!

    private lateinit var artistaAdapter : adapterartista
    private lateinit var vmodelArtista : ArtistaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fbinding =  FragmentArtistaBinding.inflate(inflater, container, false)
        val view: View = binding.root

        //----------------
        //- Personalizar el toolbar
        val toolbar: Toolbar =fbinding!!.tbArtista
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitle(getString(R.string.StrArtista))
        toolbar.setTitleTextColor(Color.WHITE)

        //**---------
        vmodelArtista = ViewModelProvider(this).get(ArtistaViewModel::class.java)
        vmodelArtista.getArtistaVM()
        artistaAdapter = adapterartista(this)
        binding.rvArtista.apply {
            layoutManager = LinearLayoutManager( view.context , LinearLayoutManager.VERTICAL, false)
            adapter=artistaAdapter
        }

        observeViewModel()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fbinding = null
    }


    fun observeViewModel()
    {
        vmodelArtista.listArtista.observe(viewLifecycleOwner, Observer < List<artista>>{ artista->
            artistaAdapter.updateData(artista) })

        vmodelArtista.isLoad.observe(viewLifecycleOwner,Observer {
            if (it != null)
                binding.progressartista.visibility = View.INVISIBLE
        })

    }


    override fun onArtistaClicked(Artista: artista, position: Int) {
        val bundle = bundleOf("artista" to Artista)
        NavHostFragment.findNavController(this).navigate(R.id.fragment_artistadet,bundle)
    }

/*
    fun getArtistas(): ArrayList<artista> {
        //--------------
        val Artista: ArrayList<artista> = ArrayList<artista>()
        //-------------
        Artista.add(artista("Armando José Aguirre", "Oleo", "Nicaragua"))
        Artista.add(artista("German Traña Obando", "Acrílico", "Panama"))
        Artista.add(artista("Pol Ledent", "Acrílico", "Ecuador"))
        Artista.add(artista("Maribel Flores", "Oleo", "Costa Rica"))
        Artista.add(artista("Nana Tchelidze", "Lápiz", "Guatemala"))
        Artista.add(artista("Armando José Aguirre", "Oleo", "El Salvador"))
        Artista.add(artista("German Traña Obando", "Lápiz", "Honduras"))
        Artista.add(artista("Pol Ledent", "Acrílico", "Chile"))
        Artista.add(artista("Maribel Flores", "Oleo", "España"))
        Artista.add(artista("Nana Tchelidze", "Lápiz", "Argentina"))
        Artista.add(artista("Armando José Aguirre", "Oleo", "Peru"))
        Artista.add(artista("German Traña Obando", "Acrílico", "Ecuador"))
        Artista.add(artista("Pol Ledent", "Acrílico", "Nicaragua"))
        Artista.add(artista("Maribel Flores", "Oleo", "Nicaragua"))
        Artista.add(artista("Nana Tchelidze", "Oleo", "Argentina"))
        return Artista
    }*/

}

