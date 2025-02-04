package com.example.sbrorientalapp.ui.carrito

import android.content.Intent
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
import com.example.sbrorientalapp.ConfirmarActivity
import com.example.sbrorientalapp.RegisterActivity
import com.example.sbrorientalapp.adapters.CarritoAdapter
import com.example.sbrorientalapp.adapters.ProductAdapter
import com.example.sbrorientalapp.databinding.FragmentCarritoBinding
import com.example.sbrorientalapp.databinding.FragmentHomeBinding

class CarritoFragment : Fragment() {

    private var _binding: FragmentCarritoBinding? = null
    private lateinit var carritoAdapter: CarritoAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarritoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        carritoAdapter = CarritoAdapter(Aplicacion.carrito)

        val recyclerView: RecyclerView = binding.viewCarrito
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.adapter = carritoAdapter

        if (Aplicacion.carrito.isEmpty()){
            binding.extendedFab.isEnabled = false
        }else{
            binding.extendedFab.isEnabled = true
        }

        updateTotalPrice()

        carritoAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                updateTotalPrice()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                updateTotalPrice()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                updateTotalPrice()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                updateTotalPrice()
            }
        })

        binding.extendedFab.setOnClickListener{
            Aplicacion.totalCarrito = Aplicacion.carrito.sumOf { it.cantidad * it.producto.precio }
            val intent = Intent(requireContext(), ConfirmarActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateTotalPrice() {
        val total = Aplicacion.carrito.sumOf { it.cantidad * it.producto.precio }
        binding.extendedFab.text = "Comprar: S/${"%.2f".format(total)}"
    }
}
