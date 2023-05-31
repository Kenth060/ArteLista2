package com.example.artelista.adapter

import com.example.artelista.model.galeria

interface galeriaListener {
    fun onGaleriaClicked(Galeria: galeria, position:Int)
}