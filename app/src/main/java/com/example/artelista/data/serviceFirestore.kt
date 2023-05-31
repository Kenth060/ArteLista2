package com.example.artelista.data

import com.example.artelista.model.artista
import com.example.artelista.model.evento
import com.example.artelista.model.galeria
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val GALERIA_COLLECTION_NAME="Galeria"
const val ARTISTA_COLLECTION_NAME="Artista"
const val EVENTO_COLLECTION_NAME="Evento"

class serviceFirestore {
    val CloudFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().build()

    init {
        //Nos permite tener los datos offline
        CloudFirestore.firestoreSettings = settings

    }

    fun getgaleria(callback: ICallback<List<galeria>>) {
        CloudFirestore.collection(GALERIA_COLLECTION_NAME)
            .orderBy("artistagaleria")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(galeria::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getartista(callback: ICallback<List<artista>>) {
        CloudFirestore.collection(ARTISTA_COLLECTION_NAME)
            .orderBy("nombreartista")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(artista::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }


    fun getevento(callback: ICallback<List<evento>>) {
        CloudFirestore.collection(EVENTO_COLLECTION_NAME)
            .orderBy("tituloevento")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(evento::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

}
