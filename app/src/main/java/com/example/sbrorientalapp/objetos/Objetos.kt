package com.example.sbrorientalapp.objetos

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.util.Calendar

@Parcelize
data class Cliente(
    var cliente_id: Int = 0,
    var nombres: String = "",
    var apellidos: String = "",
    var direccion: String = "",
    var telefono: String = "",
    var correo: String = "",
    var contraseña: String =""
): Parcelable {
    fun toJson(): String{
        return Gson().toJson(this)
    }
}

@Parcelize
data class Producto(
    var producto_id: Int = 0,
    var descripcion: String = "",
    var precio: Double = 0.0,
    var categoria_id: Int = 0,
    var imagen: String = ""
): Parcelable {
    fun toJson(): String{
        return Gson().toJson(this)
    }
}

@Parcelize
data class Categoria(
    var categoria_id: Int = 0,
    var nombre: String = ""
): Parcelable {
    fun toJson(): String{
        return Gson().toJson(this)
    }
}

@Parcelize
data class Detalle(
    var detallepedido_id: Int = 0,
    var pedido_id: Int = 0,
    var producto_id: Int = 0,
    var cantidad: Int = 0,
    var precio_unitario: Double = 0.0
): Parcelable {
    fun toJson(): String{
        return Gson().toJson(this)
    }
}

@Parcelize
data class Pedido(
    var pedido_id: Int = 0,
    var cliente_id: Int = 0,
    var trabajador_id: Int = 0,
    var forma_pago: Forma_pago = Forma_pago.tarjeta,
    var ubicacion_entrega: String = "",
    var tipo_entrega: Tipo_entrega = Tipo_entrega.delivery,
    var precio_total: Double = 0.0,
    var estado_entrega: Estado_entrega = Estado_entrega.pendiente,
    var fecha_pedido: Calendar = Calendar.getInstance(),
    var fecha_entrega: Calendar = Calendar.getInstance()
): Parcelable {
    fun toJson(): String{
        return Gson().toJson(this)
    }
}

@Parcelize
data class Trabajador(
    var trabajador_id: Int = 0,
    var nombre: String = "",
    var telefono: String = "",
    var correo: String = "",
    var dni: String = ""

): Parcelable {
    fun toJson(): String{
        return Gson().toJson(this)
    }
}

@Parcelize
data class Carrito(
    var cantidad: Int = 0,
    var producto: Producto

): Parcelable {
    fun toJson(): String{
        return Gson().toJson(this)
    }
}



enum class Forma_pago{
    efectivo,
    tarjeta,
    yape
}

enum class Tipo_entrega{
    delivery,
    recojo,
    local
}

enum class Estado_entrega{
    cancelado,
    preparando,
    pendiente,
    entregado
}