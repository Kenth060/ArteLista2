package com.example.artelista.adapter

import com.example.artelista.model.evento

interface eventoListener {

    fun onEventoClicked(Evento: evento,position:Int )
}