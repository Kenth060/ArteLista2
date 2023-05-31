package com.example.artelista.ui.galeria

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
import com.example.artelista.adapter.adaptergaleria
import com.example.artelista.adapter.galeriaListener
import com.example.artelista.databinding.FragmentGaleriaBinding
import com.example.artelista.model.galeria
import com.example.artelista.viewmodel.GaleriaViewModel
import com.google.android.gms.common.internal.FallbackServiceBroker

class GaleriaFragment : Fragment(),galeriaListener{

    private var fbinding: FragmentGaleriaBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = fbinding!!
    private lateinit var galeriaAdapter: adaptergaleria
    private lateinit var vmodelGaleria:GaleriaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fbinding = FragmentGaleriaBinding.inflate(inflater, container, false)
        val view: View = binding.root


        //--------------
        //- Personalizar el toolbar
        val toolbar: Toolbar = fbinding!!.tbGaleria
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitle(getString(R.string.strGaleria))
        toolbar.setTitleTextColor(Color.WHITE)
        //------------



        vmodelGaleria = ViewModelProvider(this).get(GaleriaViewModel::class.java)
        vmodelGaleria.getGaleriaVM()
        galeriaAdapter=adaptergaleria(this)
        binding.rvGaleria.apply {
            layoutManager=LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            adapter=galeriaAdapter
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
        vmodelGaleria.listGaleria.observe(viewLifecycleOwner, Observer < List<galeria>>{ galeria->
            galeriaAdapter.updateData(galeria) })

        vmodelGaleria.isLoad.observe(viewLifecycleOwner,Observer {
            if (it != null)
                binding.progressgaleria.visibility = View.INVISIBLE
        })

    }


    override fun onGaleriaClicked(Galeria: galeria, position: Int) {
        val bundle = bundleOf("galeria" to Galeria)
        NavHostFragment.findNavController(this).navigate(R.id.fragment_galeriadetalle,bundle)
    }


    //--Funcion para llenar galeria
    /*fun getGaleria(): ArrayList<galeria>
    {
        //--------------
        val Galeria: ArrayList<galeria> = ArrayList<galeria>()
        //-------------
        Galeria.add(galeria("Henciel Daniel Bulimar", "1,600", "https://artelista.s3.amazonaws.com/obras/big/3/5/5/1209702-607458d7c0c2a.jpg", "Memories"))
        Galeria.add(galeria("Jesús Cuenca", "1,200", "https://artelista.s3.amazonaws.com/obras/big/1/6/4/1209582.jpg", "Memories"))
        Galeria.add(galeria("Pol Ledent", "1,600", "https://artelista.s3.amazonaws.com/obras/fichas/1/4/6/1209343.jpg", "Como en invierno"))
        Galeria.add(galeria("Maribel Flores", "1,600", "https://artelista.s3.amazonaws.com/obras/fichas/7/4/0/1208970.jpg", "Blossom"))
        Galeria.add(galeria("Nana Tchelidze", "575", "https://artelista.s3.amazonaws.com/obras/big/2/3/8/1245944.jpg", "El Puente"))
        return Galeria
    }*/



}