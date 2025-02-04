package com.example.sbrorientalapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.sbrorientalapp.adapters.ConfirmarAdapter
import com.example.sbrorientalapp.databinding.ActivityConfirmarBinding
import com.example.sbrorientalapp.databinding.ActivityFinalizadoBinding

class FinalizadoActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFinalizadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinalizadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtPedido.text = "Su pedido N°"+Aplicacion.pedidoIdCarrito+"\nesta en camino"

        binding.txtRepartidor.text = "Repartidor: "+ Aplicacion.trabajador.nombre

        binding.containedButtonRegresa.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        return
    }


}