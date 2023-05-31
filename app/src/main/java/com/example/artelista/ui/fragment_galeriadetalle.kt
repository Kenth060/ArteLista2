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
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.artelista.R
import com.example.artelista.databinding.FragmentGaleriadetalleBinding
import com.example.artelista.model.galeria
import com.squareup.picasso.Picasso

class fragment_galeriadetalle : DialogFragment() {

    private var fbinding: FragmentGaleriadetalleBinding? = null
    private val binding get() = fbinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        fbinding = FragmentGaleriadetalleBinding.inflate(inflater, container, false)
        val view: View = binding.root

        var objGaleria = arguments?.getSerializable("galeria") as galeria
        binding.tvCategoriaArtista.text = objGaleria.titulogaleria
        binding.tvArtistaDet.text = objGaleria.artistagaleria
        Picasso.get().load(objGaleria.imagengaleria).into(binding.imgdetallearte)


        val toolbar: Toolbar = fbinding!!.tbGaleriaDet
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_left)
        toolbar.setTitle(getString(R.string.strGaleria))
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener{
            dismiss()
            Navigation.findNavController(it).navigateUp()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fbinding=null
    }
}
