package com.example.artelista.ui.evento

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artelista.R
import com.example.artelista.adapter.adapterartista
import com.example.artelista.adapter.adapterevento
import com.example.artelista.adapter.adaptergaleria
import com.example.artelista.adapter.eventoListener
import com.example.artelista.databinding.FragmentEventoBinding
import com.example.artelista.model.artista
import com.example.artelista.model.evento
import com.example.artelista.model.galeria
import com.example.artelista.viewmodel.ArtistaViewModel
import com.example.artelista.viewmodel.EventoViewModel


class EventoFragment : Fragment() , eventoListener {

    private var fbinding: FragmentEventoBinding? = null
    private val binding get() = fbinding!!

    private lateinit var eventoAdapter : adapterevento
    private lateinit var vmodelEvento : EventoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fbinding = FragmentEventoBinding.inflate(inflater, container, false)
        val view: View = binding.root

        //- Personalizar el toolbar
        val toolbar: Toolbar =fbinding!!.tbEvento
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitle(getString(R.string.StrArtista))
        toolbar.setTitleTextColor(Color.WHITE)


        //**---------
        vmodelEvento = ViewModelProvider(this).get(EventoViewModel::class.java)
        vmodelEvento.getEventoVM()
        eventoAdapter = adapterevento(this)
        binding.rvEvento.apply {
            layoutManager = LinearLayoutManager( view.context , LinearLayoutManager.VERTICAL, false)
            adapter=eventoAdapter
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
        vmodelEvento.listEvento.observe(viewLifecycleOwner, Observer < List<evento>>{ evento->
            eventoAdapter.updateData(evento) })

        vmodelEvento.isLoad.observe(viewLifecycleOwner,Observer {
            if (it != null)
                binding.progressevento.visibility = View.INVISIBLE
        })

    }


    override fun onEventoClicked(Evento: evento, position: Int) {
        val bundle = bundleOf("evento" to Evento)
        NavHostFragment.findNavController(this).navigate(R.id.fragment_eventodet,bundle)
    }

    /*fun getEventos(): ArrayList<evento> {
        //--------------
        val Evento: ArrayList<evento> = ArrayList<evento>()
        //-------------
        Evento.add(evento("Armando José Aguirre", "Oleo", "08:00"))
        Evento.add(evento("German Traña Obando", "Lapiz", "10:00"))
        Evento.add(evento("Pol Ledent", "Acrilico", "03:00"))
        Evento.add(evento("Maribel Flores", "Oleo", "11:00"))
        Evento.add(evento("Nana Tchelidze", "Canva", "04:00"))
        Evento.add(evento("Armando José Aguirre", "Oleo", "08:00"))
        Evento.add(evento("German Traña Obando", "Lapiz", "10:00"))
        Evento.add(evento("Pol Ledent", "Acrilico", "03:00"))
        Evento.add(evento("Maribel Flores", "Oleo", "11:00"))
        Evento.add(evento("Nana Tchelidze", "Canva", "04:00"))
        Evento.add(evento("Armando José Aguirre", "Oleo", "08:00"))
        Evento.add(evento("German Traña Obando", "Lapiz", "10:00"))
        Evento.add(evento("Pol Ledent", "Acrilico", "03:00"))
        Evento.add(evento("Maribel Flores", "Oleo", "11:00"))
        Evento.add(evento("Nana Tchelidze", "Canva", "04:00"))
        Evento.add(evento("Armando José Aguirre", "Oleo", "08:00"))
        Evento.add(evento("German Traña Obando", "Lapiz", "10:00"))
        Evento.add(evento("Pol Ledent", "Acrilico", "03:00"))
        Evento.add(evento("Maribel Flores", "Oleo", "11:00"))
        Evento.add(evento("Nana Tchelidze", "Canva", "04:00"))
        return Evento
    }*/
}