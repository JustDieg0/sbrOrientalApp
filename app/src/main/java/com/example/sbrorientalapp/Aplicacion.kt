package com.example.sbrorientalapp

import android.app.Application
import android.content.Context
import com.example.sbrorientalapp.objetos.Carrito
import com.example.sbrorientalapp.objetos.Cliente
import com.example.sbrorientalapp.objetos.Producto
import com.example.sbrorientalapp.objetos.Trabajador

class Aplicacion: Application() {
    private var resultado = ""
    companion object {
        lateinit var appContext: Context
        lateinit var resultado: String
        lateinit var productos: ArrayList<Producto>
        lateinit var carrito: MutableList<Carrito>
        lateinit var estadoLogin: String
        lateinit var estadoConfirmar: String
        var pedidoIdCarrito: Int = 0
        var totalCarrito: Double = 0.0
        var isDelivery: Boolean = false
        lateinit var cliente:Cliente
        lateinit var trabajador: Trabajador
        fun initialize() {
            resultado = "PRIMER VALOR"
            productos = ArrayList()
            carrito = ArrayList()
            cliente = Cliente()
        }
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initialize()
    }
}