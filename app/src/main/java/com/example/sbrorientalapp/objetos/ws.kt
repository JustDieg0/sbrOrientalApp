package com.example.sbrorientalapp.objetos

import android.util.Log
import com.example.sbrorientalapp.Aplicacion
import com.example.sbrorientalapp.network.ApiService
import com.example.sbrorientalapp.network.response.ObjetosResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

class ws{
    companion object {
        fun obtenerCliente(){
            val BASE_URL = Constantes.BASE_URL // Replace with your API base URL
            val apiService: ApiService by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(ApiService::class.java)
            }
            apiService.getCliente().enqueue(object :
                Callback<ObjetosResponse.selectClienteResponse> {
                override fun onResponse(call: Call<ObjetosResponse.selectClienteResponse>,
                                        response: Response<ObjetosResponse.selectClienteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()?.success ?: false
                        val people = response.body()?.data
                        Log.d("respuesta: ",respuesta.toString())
                        var i = 0
                        people?.forEach { person ->
                            Log.d("persona N° $i: ",person.toString())
                            i++
                        }
                    } else {
                        Log.d("no respuesta: ","")
                    }
                }
                override fun onFailure(call: Call<ObjetosResponse.selectClienteResponse>, t: Throwable) {
                    Log.d("no respuesta fallo: ",t.toString())
                }
            })
        }

        fun obtenerClienteById(id: Int){
            val BASE_URL = Constantes.BASE_URL // Replace with your API base URL
            val apiService: ApiService by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(ApiService::class.java)
            }
            apiService.getClienteById(id).enqueue(object :
                Callback<ObjetosResponse.selectClienteResponse> {
                override fun onResponse(call: Call<ObjetosResponse.selectClienteResponse>,
                                        response: Response<ObjetosResponse.selectClienteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()?.success ?: false
                        val people = response.body()?.data
                        Log.d("respuesta: ",respuesta.toString())
                        var i = 0
                        people?.forEach { person ->
                            Log.d("persona N° $i: ",person.toString())
                            i++
                        }
                    } else {
                        Log.d("no respuesta: ","")
                    }
                }
                override fun onFailure(call: Call<ObjetosResponse.selectClienteResponse>, t: Throwable) {
                    Log.d("no respuesta fallo: ",t.toString())
                }
            })
        }

        fun obtenerClienteByCorreoContra(correo:String, contraseña:String,callback: (Boolean) -> Unit){
            val BASE_URL = Constantes.BASE_URL // Replace with your API base URL
            val flag = false
            val cliente = Cliente()
            cliente.correo = correo
            cliente.contraseña = contraseña
            val apiService: ApiService by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(ApiService::class.java)
            }
            apiService.getClienteByCorreoContra(cliente).enqueue(object :
                Callback<ObjetosResponse.selectClienteResponse> {
                override fun onResponse(call: Call<ObjetosResponse.selectClienteResponse>,
                                        response: Response<ObjetosResponse.selectClienteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()?.success ?: false
                        val people = response.body()?.data
                        Log.d("respuesta: ",respuesta.toString())
                        var i = 0
                        Aplicacion.estadoLogin = "Correo o contraseña incorrectos"
                        callback(false)
                        people?.forEach { person ->
                            Aplicacion.cliente.cliente_id = person.cliente_id
                            Aplicacion.cliente.nombres = person.nombres
                            Aplicacion.cliente.apellidos = person.apellidos
                            Aplicacion.cliente.correo = person.correo
                            Aplicacion.cliente.contraseña = person.contraseña
                            Aplicacion.cliente.direccion = person.direccion
                            Aplicacion.cliente.telefono = person.telefono

                            Log.d("persona N° $i: ",person.toString())
                            i++
                            callback(true)
                        }
                    } else {
                        Log.d("no respuesta: ","")
                        Aplicacion.estadoLogin = "Correo o contraseña incorrectos"
                        callback(false)
                    }
                }
                override fun onFailure(call: Call<ObjetosResponse.selectClienteResponse>, t: Throwable) {
                    Log.d("no respuesta fallo: ",t.toString())
                    Aplicacion.estadoLogin = "No se pudo establecer la conexión"
                    callback(false)
                }
            })
        }

        fun insertCliente(cliente: Cliente){
            val BASE_URL = Constantes.BASE_URL // Replace with your API base URL
            val apiService: ApiService by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(ApiService::class.java)
            }
            apiService.insertCliente(cliente).enqueue(object :
                Callback<ObjetosResponse.insertClienteResponse> {
                override fun onResponse(call: Call<ObjetosResponse.insertClienteResponse>,
                                        response: Response<ObjetosResponse.insertClienteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()?.success ?: false
                        Log.d("respuesta: ",respuesta.toString())
                    } else {
                        Log.d("no respuesta: ","")
                    }
                }
                override fun onFailure(call: Call<ObjetosResponse.insertClienteResponse>, t: Throwable) {
                    Log.d("no respuesta fallo: ",t.toString())
                }
            })
        }

        fun obtenerProductos(){
            val BASE_URL = Constantes.BASE_URL // Replace with your API base URL
            val apiService: ApiService by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(ApiService::class.java)
            }
            apiService.getProducto().enqueue(object :
                Callback<ObjetosResponse.selectProductoResponse> {
                override fun onResponse(call: Call<ObjetosResponse.selectProductoResponse>,
                                        response: Response<ObjetosResponse.selectProductoResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()?.success ?: false
                        val inventario = response.body()?.data
                        Log.d("respuesta: ",respuesta.toString())

                        Aplicacion.productos.clear()

                        var i = 0
                        inventario?.forEach { producto ->
                            Log.d("producto N° $i: ",producto.toString())
                            val nuevoProducto = Producto(
                                producto_id = producto.producto_id,
                                descripcion = producto.descripcion,
                                precio = producto.precio,
                                categoria_id = producto.categoria_id,
                                imagen = producto.imagen)
                            i++
                            Aplicacion.productos.add(nuevoProducto)
                        }
                    } else {
                        Log.d("no respuesta: ","")
                    }
                }
                override fun onFailure(call: Call<ObjetosResponse.selectProductoResponse>, t: Throwable) {
                    Log.d("no respuesta fallo: ",t.toString())
                }
            })
        }

        fun insertarPedido(cliente_id:Int, formaPago: Forma_pago, ubicacion_entrega:String, tipo_entrega:Tipo_entrega, precio_total:Double, estado_entrega: Estado_entrega,callback: (Boolean) -> Unit){
            val BASE_URL = Constantes.BASE_URL // Replace with your API base URL
            val flag = false
            val pedido = Pedido()
            pedido.cliente_id = cliente_id
            pedido.forma_pago = formaPago
            pedido.ubicacion_entrega = ubicacion_entrega
            pedido.tipo_entrega = tipo_entrega
            pedido.precio_total = precio_total
            pedido.estado_entrega = estado_entrega
            val apiService: ApiService by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(ApiService::class.java)
            }
            apiService.insertarPedido(pedido).enqueue(object :
                Callback<ObjetosResponse.insertPedidoResponse> {
                override fun onResponse(call: Call<ObjetosResponse.insertPedidoResponse>,
                                        response: Response<ObjetosResponse.insertPedidoResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()?.success ?: false
                        val people = response.body()?.data
                        Log.d("respuesta: ",respuesta.toString())
                        var i = 0
                        Aplicacion.estadoConfirmar = ""
                        callback(false)
                        people?.forEach { person ->
                            Aplicacion.pedidoIdCarrito = person.pedido_id
                            Log.d("pedido_id N° $i: ",person.toString())
                            i++
                            callback(true)
                        }
                    } else {
                        Log.d("no respuesta: ","")
                        Aplicacion.estadoConfirmar = "Hubo un error"
                        callback(false)
                    }
                }
                override fun onFailure(call: Call<ObjetosResponse.insertPedidoResponse>, t: Throwable) {
                    Log.d("no respuesta fallo: ",t.toString())
                    Aplicacion.estadoConfirmar = "No se logró la conexión"
                    callback(false)
                }
            })
        }

        fun insertarDetalle(pedido_id:Int, producto_id:Int, cantidad: Int, precio_unitario:Double,callback: (Boolean) -> Unit){
            val BASE_URL = Constantes.BASE_URL // Replace with your API base URL
            val flag = false
            val detalle = Detalle()
            detalle.pedido_id = pedido_id
            detalle.producto_id = producto_id
            detalle.cantidad = cantidad
            detalle.precio_unitario = precio_unitario
            val apiService: ApiService by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(ApiService::class.java)
            }
            apiService.insertarDetalle(detalle).enqueue(object :
                Callback<ObjetosResponse.insertDetalleResponse> {
                override fun onResponse(call: Call<ObjetosResponse.insertDetalleResponse>,
                                        response: Response<ObjetosResponse.insertDetalleResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()?.success ?: false
                        val people = response.body()?.data
                        Log.d("respuesta: ",respuesta.toString())
                        var i = 0
                        Aplicacion.estadoConfirmar = ""
                        callback(false)
                        people?.forEach { person ->
                            Log.d("detalle N° $i: ",person.toString())
                            i++
                            callback(true)
                        }
                    } else {
                        Log.d("no respuesta: ","")
                        Aplicacion.estadoConfirmar = "Hubo un error"
                        callback(false)
                    }
                }
                override fun onFailure(call: Call<ObjetosResponse.insertDetalleResponse>, t: Throwable) {
                    Log.d("no respuesta fallo: ",t.toString())
                    Aplicacion.estadoConfirmar = "No se logró la conexión"
                    callback(false)
                }
            })
        }

        fun obtenerTrabajadorPedido(id:Int,callback: (Boolean) -> Unit){
            val BASE_URL = Constantes.BASE_URL // Replace with your API base URL
            val apiService: ApiService by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(ApiService::class.java)
            }
            apiService.getTrabajadorPedido(id).enqueue(object :
                Callback<ObjetosResponse.selectTrabajadorResponse> {
                override fun onResponse(call: Call<ObjetosResponse.selectTrabajadorResponse>,
                                        response: Response<ObjetosResponse.selectTrabajadorResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()?.success ?: false
                        val inventario = response.body()?.data
                        Log.d("respuesta: ",respuesta.toString())

                        var i = 0

                        callback(false)
                        inventario?.forEach { trabajador ->
                            Log.d("producto N° $i: ",trabajador.toString())
                            Aplicacion.trabajador = trabajador
                            callback(true)
                        }
                    } else {
                        Log.d("no respuesta: ","")
                        callback(false)
                    }
                }
                override fun onFailure(call: Call<ObjetosResponse.selectTrabajadorResponse>, t: Throwable) {
                    Log.d("no respuesta fallo: ",t.toString())
                    callback(false)
                }
            })
        }
    }
}
