package com.example.artelista.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.artelista.R
import com.example.artelista.model.artista
import com.example.artelista.model.galeria

class adapterartista (val ArtistaListener: artistaListener) : RecyclerView.Adapter<adapterartista.ArtistaViewHolder>()
{

    var listArtista= ArrayList<artista>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artista, parent, false)
        return ArtistaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
        val artista: artista = listArtista[position]
        holder.tvNombreArtista.setText(artista.nombreartista)
        holder.tvCategoriaArtista.setText(artista.categoriaartista)
        holder.tvPaisArtista.setText(artista.paisartista)

        holder.itemView.setOnClickListener{
            ArtistaListener.onArtistaClicked(artista,position)

            //Navigation.findNavController(holder.itemView).navigate(
              //  R.id.fragment_artistadet)
        }

    }

    //---------------------------

    fun updateData(data:List<artista>?)
    {
        listArtista.clear()
        listArtista.addAll(data!!)
        notifyDataSetChanged()
    }

    //------------------------------
    override fun getItemCount(): Int {
        return listArtista.size
    }
    //Adapter versus la interface de cada item del recycler
    inner class ArtistaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //-------------------------
        val tvNombreArtista: TextView
        val tvCategoriaArtista: TextView
        val tvPaisArtista: TextView
        //-----------------------
        init {
            tvNombreArtista = itemView.findViewById<View>(R.id.tvNombreArtista) as TextView
            tvCategoriaArtista = itemView.findViewById<View>(R.id.tvCategoriaArtista) as TextView
            tvPaisArtista = itemView.findViewById<View>(R.id.tvPaisArtista) as TextView
        }
    }
}