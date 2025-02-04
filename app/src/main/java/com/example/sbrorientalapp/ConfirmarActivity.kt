package com.example.sbrorientalapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sbrorientalapp.adapters.CarritoAdapter
import com.example.sbrorientalapp.adapters.ConfirmarAdapter
import com.example.sbrorientalapp.databinding.ActivityConfirmarBinding
import com.example.sbrorientalapp.objetos.Carrito
import com.example.sbrorientalapp.objetos.Constantes
import com.example.sbrorientalapp.objetos.Estado_entrega
import com.example.sbrorientalapp.objetos.Forma_pago
import com.example.sbrorientalapp.objetos.Tipo_entrega
import com.example.sbrorientalapp.objetos.ws

class ConfirmarActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityConfirmarBinding
    private lateinit var confirmarAdapter: ConfirmarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConfirmarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemsFP = Forma_pago.entries.map { it.name }
        val adapterFP = ArrayAdapter(this, R.layout.item_list,R.id.opcMenu, itemsFP)
        binding.opcFormaDePago.setAdapter(adapterFP)
        binding.opcFormaDePago.setText(Forma_pago.efectivo.name, false)

        confirmarAdapter = ConfirmarAdapter(Aplicacion.carrito)

        val recyclerView: RecyclerView = binding.viewCarritoConfirmar
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = confirmarAdapter

        val itemsTE = Tipo_entrega.entries.map { it.name }
        val adapterTE = ArrayAdapter(this, R.layout.item_list,R.id.opcMenu, itemsTE)
        binding.opcTipoEntrega.setAdapter(adapterTE)
        binding.opcTipoEntrega.setText(Tipo_entrega.delivery.name, false)
        isDelivery(true)

        binding.opcTipoEntrega.setOnItemClickListener { parent, view, position, id ->
            when (Tipo_entrega.entries[position]) {
                Tipo_entrega.delivery -> isDelivery(true)
                Tipo_entrega.local -> isDelivery(false)
                Tipo_entrega.recojo -> isDelivery(false)
            }
        }

        binding.opcFormaDePago.setOnItemClickListener { parent, view, position, id ->
            when (Forma_pago.entries[position]) {
                Forma_pago.efectivo -> mostrarCamposParaEfectivo()
                Forma_pago.tarjeta -> mostrarCamposParaTarjeta()
                Forma_pago.yape -> mostrarCamposParaYape()
            }
        }

        val formasDePago = mapOf(
            "efectivo" to Forma_pago.efectivo,
            "tarjeta" to Forma_pago.tarjeta,
            "yape" to Forma_pago.yape
        )

        val tipoDeEntrega = mapOf(
            "delivery" to Tipo_entrega.delivery,
            "recojo" to Tipo_entrega.recojo,
            "local" to Tipo_entrega.local
        )

        var frm_pago: Forma_pago
        var tp_entrg: Tipo_entrega

        binding.extendedFab.setOnClickListener{
            val cli_id = Aplicacion.cliente.cliente_id
            frm_pago = formasDePago[binding.opcFormaDePago.text.toString()]!!
            var ubi_entrg = binding.edDireccion.text.toString()
            tp_entrg = tipoDeEntrega[binding.opcTipoEntrega.text.toString()]!!
            var pc_tot = Aplicacion.totalCarrito
            val estEtrg = Estado_entrega.pendiente
            if (Aplicacion.isDelivery){
                ubi_entrg = tp_entrg.toString()
                pc_tot += 5
            }
            ws.insertarPedido(cli_id,frm_pago,ubi_entrg,tp_entrg,pc_tot,estEtrg){ flaggApp ->
                if (flaggApp){
                    ws.obtenerTrabajadorPedido(Aplicacion.pedidoIdCarrito){flagTrb ->
                        if(flagTrb){
                            for (item in Aplicacion.carrito){
                                var flag2 = true
                                ws.insertarDetalle(Aplicacion.pedidoIdCarrito,item.producto.producto_id,item.cantidad,item.producto.precio){flaggApp2 ->
                                    if (flaggApp2){
                                        flag2 = false
                                    }
                                }
                            }
                            val intent = Intent(this, FinalizadoActivity::class.java)
                            startActivity(intent)
                            Aplicacion.carrito.clear()
                        }
                    }
                }else{

                }

            }
        }

        configurarMascaraNumeroTarjeta()
        configurarCampoFechaVencimiento()
        return
    }

    private fun mostrarCamposParaEfectivo() {
        binding.campoMontoEfectivo.visibility = View.VISIBLE
        binding.campoCelularYape.visibility = View.GONE
        binding.campoCodigoYape.visibility = View.GONE
        binding.campoNumeroTarjeta.visibility = View.GONE
        binding.campoCvvTarjeta.visibility = View.GONE
        binding.campoFechaVencimientoTarjeta.visibility = View.GONE
    }

    private fun mostrarCamposParaTarjeta() {
        binding.campoMontoEfectivo.visibility = View.GONE
        binding.campoCelularYape.visibility = View.GONE
        binding.campoCodigoYape.visibility = View.GONE
        binding.campoNumeroTarjeta.visibility = View.VISIBLE
        binding.campoCvvTarjeta.visibility = View.VISIBLE
        binding.campoFechaVencimientoTarjeta.visibility = View.VISIBLE
    }

    private fun mostrarCamposParaYape() {
        binding.campoMontoEfectivo.visibility = View.GONE
        binding.campoCelularYape.visibility = View.VISIBLE
        binding.campoCodigoYape.visibility = View.VISIBLE
        binding.campoNumeroTarjeta.visibility = View.GONE
        binding.campoCvvTarjeta.visibility = View.GONE
        binding.campoFechaVencimientoTarjeta.visibility = View.GONE
    }

    private fun configurarCampoFechaVencimiento() {
        val campoFecha = binding.edFechaVencimientoTarjeta
        campoFecha.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val mask = "##/##"

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return

                isUpdating = true

                val input = s.toString().replace("/", "").take(4)
                val formatted = when (input.length) {
                    in 1..2 -> input
                    in 3..4 -> input.substring(0, 2) + "/" + input.substring(2)
                    else -> ""
                }

                campoFecha.setText(formatted)
                campoFecha.setSelection(formatted.length)
                isUpdating = false
            }
        })
    }

    private fun configurarMascaraNumeroTarjeta() {
        val campoNumeroTarjeta = binding.edNumeroTarjeta
        campoNumeroTarjeta.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return

                isUpdating = true
                val input = s.toString().replace(" ", "").take(16)
                val formatted = input.chunked(4).joinToString(" ")
                campoNumeroTarjeta.setText(formatted)
                campoNumeroTarjeta.setSelection(formatted.length)
                isUpdating = false
            }
        })
    }

    private fun isDelivery(deli :Boolean){
        Aplicacion.isDelivery = deli
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        var deli = 0.0
        if (Aplicacion.isDelivery){
            deli = 5.0
            binding.deliveryConfirmar.visibility = View.VISIBLE
            binding.txedDireccion.visibility = View.VISIBLE
        }else{
            binding.deliveryConfirmar.visibility = View.GONE
            binding.txedDireccion.visibility = View.GONE
        }
        val total = Aplicacion.carrito.sumOf { it.cantidad * it.producto.precio } + deli
        binding.totalConfirmar.text = "Total: S/${"%.2f".format(total)}"
    }
}