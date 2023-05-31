package com.example.artelista.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artelista.data.ICallback
import com.example.artelista.data.serviceFirestore
import com.example.artelista.model.evento
import com.example.artelista.model.galeria

class EventoViewModel : ViewModel()
{

    val firestoreService= serviceFirestore()
    var listEvento: MutableLiveData<List<evento>> = MutableLiveData()
    var isLoad = MutableLiveData<Boolean>()

    //GetEvento
    fun getEventoVM(){
        firestoreService.getevento(object : ICallback<List<evento>> {
            override fun onSuccess(result: List<evento>?) {
                listEvento.postValue(result!!)
                CargaFinalizada()
            }

            override fun onFailed(exception: Exception) {

            }
        })
    }

    fun CargaFinalizada()
    {  isLoad.value=true }


}