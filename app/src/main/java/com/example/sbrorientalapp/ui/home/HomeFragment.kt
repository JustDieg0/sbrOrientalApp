package com.example.sbrorientalapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sbrorientalapp.Aplicacion
import com.example.sbrorientalapp.adapters.ProductAdapter
import com.example.sbrorientalapp.databinding.FragmentHomeBinding
import com.example.sbrorientalapp.objetos.ws

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var productoAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        ws.obtenerProductos()

        productoAdapter = ProductAdapter(Aplicacion.productos)

        val recyclerView: RecyclerView = binding.viewProductos
        recyclerView.layoutManager = GridLayoutManager(context,2)

        recyclerView.adapter = productoAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}