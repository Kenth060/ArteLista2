package com.example.artelista.adapter

import com.example.artelista.model.artista

interface artistaListener
{
    fun onArtistaClicked(Artista: artista, position:Int)
}