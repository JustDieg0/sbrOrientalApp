package com.example.sbrorientalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.sbrorientalapp.databinding.ActivityMainBinding
import com.example.sbrorientalapp.databinding.ActivityLoginBinding
import com.example.sbrorientalapp.objetos.ws

class LoginActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = binding.containedButton
        ws.obtenerProductos()
        button.setOnClickListener {
            var validacion = true
            binding.txedUsuario.setHelperTextColor(binding.root.context.getColorStateList(R.color.red_600))
            binding.txedUsuario.setHelperText("")

            if (binding.edUsuario.text.toString().isEmpty()) {
                Toast.makeText(binding.root.context, "Campos sin llenar", Toast.LENGTH_LONG).show()
                binding.txedUsuario.setHelperText("Campo sin llenar")
                validacion = false
            }

            binding.txedPassword.setHelperTextColor(binding.root.context.getColorStateList(R.color.red_600))
            binding.txedPassword.setHelperText("")

            if (binding.edPassword.text.toString().isEmpty()) {
                binding.txedPassword.setHelperText("Campo sin llenar")
                validacion = false
            }

            if (!validacion) return@setOnClickListener

            ws.obtenerClienteByCorreoContra(binding.edUsuario.text.toString(), binding.edPassword.text.toString()) { flagApp ->
                if (flagApp) {
                    val texto = "Usuario: ${binding.edUsuario.text}\nContraseña: ${binding.edPassword.text}"
                    binding.txedPassword.setHelperText("")
                    Aplicacion.resultado = texto
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    binding.txedPassword.setHelperText(Aplicacion.estadoLogin)
                }
            }
        }

        val button2 = binding.outlinedButton
        button2.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        return
    }
}