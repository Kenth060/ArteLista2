package com.example.artelista.adapter
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.artelista.R
import com.example.artelista.model.galeria
import com.squareup.picasso.Picasso

class adaptergaleria(val GaleriaListener: galeriaListener) :
    RecyclerView.Adapter<adaptergaleria.GaleriaViewHolder>() {

    val listGaleria=ArrayList<galeria>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_galeria, parent, false)
        return GaleriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: GaleriaViewHolder, position: Int) {
        val galeria: galeria = listGaleria[position]
        holder.tvArtistaGaleria.setText(galeria.artistagaleria)
        holder.tvPrecioGaleria.setText(galeria.preciogaleria)
        holder.tvTituloGaleria.setText(galeria.titulogaleria)
        Picasso.get().load(galeria.imagengaleria).into(holder.imgGaleria)

        holder.itemView.setOnClickListener{
            GaleriaListener.onGaleriaClicked(galeria,position)
            /*Navigation.findNavController(holder.itemView).navigate(
                R.id.fragment_galeriadetalle)*/
        }

    }
    fun updateData(data:List<galeria>?)
    {
        listGaleria.clear()
        listGaleria.addAll(data!!)
        notifyDataSetChanged()
    }
    //------------------------------
    override fun getItemCount(): Int {
        return listGaleria.size
    }
    //Adapter versus la interface de cada item del recycler
    inner class GaleriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //-------------------------
        val tvArtistaGaleria: TextView
        val tvPrecioGaleria: TextView
        val tvTituloGaleria: TextView
        val imgGaleria: ImageView
        //-----------------------
        init {
            tvArtistaGaleria = itemView.findViewById<View>(R.id.tvArtista) as TextView
            tvPrecioGaleria = itemView.findViewById<View>(R.id.tvPrecioArte) as TextView
            tvTituloGaleria = itemView.findViewById<View>(R.id.tvTituloArte) as TextView
            imgGaleria = itemView.findViewById<View>(R.id.imgArteGaleria) as ImageView
        }
    }
}