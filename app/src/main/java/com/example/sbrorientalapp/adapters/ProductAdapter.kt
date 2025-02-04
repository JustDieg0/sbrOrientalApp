package com.example.sbrorientalapp.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sbrorientalapp.Aplicacion
import com.example.sbrorientalapp.R
import com.example.sbrorientalapp.objetos.Carrito
import com.example.sbrorientalapp.objetos.Constantes
import com.example.sbrorientalapp.objetos.Producto
import com.squareup.picasso.Picasso

class ProductAdapter(private val productList: List<Producto>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val descripcionView: TextView = itemView.findViewById(R.id.descripcionProducto)
        private val precioView: TextView = itemView.findViewById(R.id.precioProducto)
        private val imgView: ImageView = itemView.findViewById(R.id.imgProducto)
        private val botonView: ImageButton = itemView.findViewById(R.id.btnCompra)

        fun bind(producto: Producto) {
            descripcionView.text = producto.descripcion
            precioView.text = "S/"+producto.precio.toString()

            val productoEnCarrito = Aplicacion.carrito.find { it.producto == producto }

            if (productoEnCarrito != null) {
                alternarBoton(botonView,false)
            } else {
                alternarBoton(botonView,true)
            }

            botonView.setOnClickListener{
                    Aplicacion.carrito.add(Carrito(1, producto))
                    alternarBoton(botonView,false)
            }
            Picasso.get()
                .load(Constantes.BASE_URL+"img/"+producto.imagen)
                .error(R.drawable.chaufa_icon)
                .into(imgView)
        }

        fun alternarBoton(boton: ImageButton, activa: Boolean){
            if (activa){
                boton.setBackgroundResource(R.drawable.bg_circle_button_selector)
                boton.isEnabled = true
            }else{
                boton.setBackgroundResource(R.drawable.bg_circle_button_selector)
                boton.isEnabled = false
            }
        }
    }
}
