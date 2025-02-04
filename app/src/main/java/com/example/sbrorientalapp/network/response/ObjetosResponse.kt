package com.example.sbrorientalapp.network.response

import com.example.sbrorientalapp.objetos.Cliente
import com.example.sbrorientalapp.objetos.Detalle
import com.example.sbrorientalapp.objetos.Pedido
import com.example.sbrorientalapp.objetos.Producto
import com.example.sbrorientalapp.objetos.Trabajador

class ObjetosResponse {
    data class selectClienteResponse(
        var data: List<Cliente>,
        var success : Boolean,
        var error: String
    )

    data class insertClienteResponse(
        var success: Boolean,
        var error: String
    )

    data class selectProductoResponse(
        var data: List<Producto>,
        var success : Boolean,
        var error: String
    )

    data class insertProductoResponse(
        var success: Boolean,
        var error: String
    )

    data class selectCategoriaResponse(
        var data: List<Cliente>,
        var success : Boolean,
        var error: String
    )

    data class insertCategoriaResponse(
        var success: Boolean,
        var error: String
    )

    data class insertPedidoResponse(
        var data: List<Pedido>,
        var success: Boolean,
        var error: String
    )

    data class insertDetalleResponse(
        var data: List<Detalle>,
        var success: Boolean,
        var error: String
    )

    data class selectTrabajadorResponse(
        var data: List<Trabajador>,
        var success : Boolean,
        var error: String
    )
}