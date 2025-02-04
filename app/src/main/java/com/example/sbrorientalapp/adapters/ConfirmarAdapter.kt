package com.example.sbrorientalapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sbrorientalapp.R
import com.example.sbrorientalapp.objetos.Carrito
import com.example.sbrorientalapp.objetos.Constantes
import com.squareup.picasso.Picasso

class ConfirmarAdapter(private val carritoList: MutableList<Carrito>) :
    RecyclerView.Adapter<ConfirmarAdapter.CarritoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_confirmar, parent, false)
        return CarritoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val carrito = carritoList[position]
        holder.bind(carrito)
    }

    override fun getItemCount(): Int = carritoList.size

    inner class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cantidadView: TextView = itemView.findViewById(R.id.cantidadConfirmar)
        private val descripcionView: TextView = itemView.findViewById(R.id.nombreConfirmar)
        private val precioView: TextView = itemView.findViewById(R.id.precioConfirmar)

        fun bind(carrito: Carrito) {
            val producto = carrito.producto
            cantidadView.text = "x"+carrito.cantidad.toString()
            descripcionView.text = producto.descripcion
            precioView.text = "S/" + (carrito.cantidad * carrito.producto.precio).toString()
        }
    }
}