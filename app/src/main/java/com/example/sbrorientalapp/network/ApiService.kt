package com.example.sbrorientalapp.network

import com.example.sbrorientalapp.network.response.ObjetosResponse
import com.example.sbrorientalapp.objetos.Categoria
import com.example.sbrorientalapp.objetos.Cliente
import com.example.sbrorientalapp.objetos.Detalle
import com.example.sbrorientalapp.objetos.Pedido
import com.example.sbrorientalapp.objetos.Producto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Para clientes
    @GET("cliente")
    fun getCliente(): Call<ObjetosResponse.selectClienteResponse>

    @GET("cliente")
    fun getClienteById(@Path("id") id: Int): Call<ObjetosResponse.selectClienteResponse>

    @POST("login")
    fun getClienteByCorreoContra(
        @Body cliente: Cliente
    ): Call<ObjetosResponse.selectClienteResponse>

    @POST("cliente")
    fun insertCliente(
        @Body cliente: Cliente
    ): Call<ObjetosResponse.insertClienteResponse>

    @PUT("cliente")
    fun updateCliente(
        @Body cliente: Cliente
    ): Call<ObjetosResponse.insertClienteResponse>

    @DELETE("cliente")
    fun deleteCliente(
        @Body cliente: Cliente
    ): Call<ObjetosResponse.insertClienteResponse>

    //Para Productos
    @GET("producto")
    fun getProducto(): Call<ObjetosResponse.selectProductoResponse>

    @POST("producto")
    fun insertProducto(
        @Body producto: Producto
    ): Call<ObjetosResponse.insertProductoResponse>

    @PUT("producto")
    fun updateProducto(
        @Body producto: Producto
    ): Call<ObjetosResponse.insertProductoResponse>

    @DELETE("producto")
    fun deleteProducto(
        @Body producto: Producto
    ): Call<ObjetosResponse.insertProductoResponse>

    //Para Categoria
    @GET("categoria")
    fun getCategoria(): Call<ObjetosResponse.selectCategoriaResponse>

    @POST("categoria")
    fun insertCategoria(
        @Body categoria: Categoria
    ): Call<ObjetosResponse.insertCategoriaResponse>

    @PUT("categoria")
    fun updateCategoria(
        @Body categoria: Categoria
    ): Call<ObjetosResponse.insertCategoriaResponse>

    @DELETE("categoria")
    fun deleteCategoria(
        @Body categoria: Categoria
    ): Call<ObjetosResponse.insertCategoriaResponse>

    @POST("pedido")
    fun insertarPedido(
        @Body pedido: Pedido
    ): Call<ObjetosResponse.insertPedidoResponse>

    @POST("detallepedido")
    fun insertarDetalle(
        @Body detalle: Detalle
    ): Call<ObjetosResponse.insertDetalleResponse>

    @GET("trabajador/pedido/{id}")
    fun getTrabajadorPedido(@Path("id") id: Int): Call<ObjetosResponse.selectTrabajadorResponse>
}