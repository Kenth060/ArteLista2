package com.example.artelista.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artelista.data.ICallback
import com.example.artelista.data.serviceFirestore
import com.example.artelista.model.galeria

class GaleriaViewModel:ViewModel() {
    //Inicializacion
    val firestoreService=serviceFirestore()
    var listGaleria:MutableLiveData<List<galeria>> = MutableLiveData()
    var isLoad = MutableLiveData<Boolean>()

    //GetGaleria
    fun getGaleriaVM(){
        firestoreService.getgaleria(object :ICallback<List<galeria>>{
            override fun onSuccess(result: List<galeria>?) {
                listGaleria.postValue(result!!)
                CargaFinalizada()
            }

            override fun onFailed(exception: Exception) {

            }
        })
    }

    fun CargaFinalizada()
    {  isLoad.value=true }
}