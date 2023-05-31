@file:Suppress("DEPRECATION")
package com.example.artelista.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import com.example.artelista.R
import com.example.artelista.databinding.FragmentArtistadetBinding
import com.example.artelista.model.artista
import com.example.artelista.model.galeria

class fragment_artistadet : DialogFragment() {

    private var fbinding: FragmentArtistadetBinding? = null
    private val binding get() = fbinding!!

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        fbinding =  FragmentArtistadetBinding.inflate(inflater, container, false)
        val view: View = binding.root

        //-------------

        var objArtista = arguments?.getSerializable("artista") as artista
        binding.tvNombreArtistaDet.text = objArtista.nombreartista
        binding.tvPaisArtista.text = objArtista.paisartista
        binding.tvAcercaArtistaDet.text = objArtista.categoriaartista


        //**********************
        val toolbar: Toolbar =fbinding!!.tbArtistaDet
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.navigationIcon=ContextCompat.getDrawable(view.context, R.drawable.ic_left)
        toolbar.setTitle("Datos del Artista")
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener{
            dismiss()
            Navigation.findNavController(it).navigateUp()
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fbinding = null
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}