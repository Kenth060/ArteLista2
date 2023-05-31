package com.example.artelista.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artelista.data.ICallback
import com.example.artelista.data.serviceFirestore
import com.example.artelista.model.artista
import com.example.artelista.model.galeria

class ArtistaViewModel : ViewModel()
{
    //Inicializacion
    val firestoreService= serviceFirestore()
    var listArtista: MutableLiveData<List<artista>> = MutableLiveData()
    var isLoad = MutableLiveData<Boolean>()


    fun getArtistaVM(){
        firestoreService.getartista(object : ICallback<List<artista>> {
            override fun onSuccess(result: List<artista>?) {
                listArtista.postValue(result!!)
                CargaFinalizada()
            }

            override fun onFailed(exception: Exception) {

            }
        })
    }

    fun CargaFinalizada()
    {  isLoad.value=true }

}