package com.example.sbrorientalapp.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sbrorientalapp.Aplicacion
import com.example.sbrorientalapp.R
import com.example.sbrorientalapp.objetos.Carrito
import com.example.sbrorientalapp.objetos.Constantes
import com.squareup.picasso.Picasso

class CarritoAdapter(private val carritoList: MutableList<Carrito>) :
    RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val carrito = carritoList[position]
        holder.bind(carrito)
    }

    override fun getItemCount(): Int = carritoList.size

    inner class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cantidadView: TextView = itemView.findViewById(R.id.cantidadCarrito)
        private val descripcionView: TextView = itemView.findViewById(R.id.nombreProductoCarrito)
        private val precioView: TextView = itemView.findViewById(R.id.precioProductoCarrito)
        private val imgView: ImageView = itemView.findViewById(R.id.imgCarrito)
        private val btnAgregarView: ImageButton = itemView.findViewById(R.id.btnAgregarCarrito)
        private val btnRestarView: ImageButton = itemView.findViewById(R.id.btnRestarCarrito)
        private val btnEliminarView: ImageButton = itemView.findViewById(R.id.btnEliminarCarrito)

        fun bind(carrito: Carrito) {
            val producto = carrito.producto
            cantidadView.text = "x"+carrito.cantidad.toString()
            descripcionView.text = producto.descripcion
            precioView.text = "S/" + (carrito.cantidad * carrito.producto.precio).toString()

            Picasso.get()
                .load(Constantes.BASE_URL + "img/" + producto.imagen)
                .error(R.drawable.chaufa_icon)
                .into(imgView)

            btnAgregarView.setOnClickListener {
                carrito.cantidad += 1
                notifyItemChanged(adapterPosition) // Notificar al adaptador
            }

            btnRestarView.setOnClickListener {
                val nuevaCantidad = carrito.cantidad - 1
                if (nuevaCantidad == 0) {
                    carritoList.removeAt(adapterPosition) // Eliminar el elemento
                    notifyItemRemoved(adapterPosition) // Notificar eliminación
                } else {
                    carrito.cantidad = nuevaCantidad
                    notifyItemChanged(adapterPosition) // Notificar cambio
                }
            }
            btnEliminarView.setOnClickListener{
                carritoList.removeAt(adapterPosition) // Eliminar el elemento
                notifyItemRemoved(adapterPosition) // Notificar eliminación
            }
        }
    }
}
