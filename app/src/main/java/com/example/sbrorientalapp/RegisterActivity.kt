package com.example.sbrorientalapp

import android.content.Intent
import android.os.Bundle
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
import com.example.sbrorientalapp.databinding.ActivityRegisterBinding
import com.example.sbrorientalapp.objetos.Cliente
import com.example.sbrorientalapp.objetos.ws

class RegisterActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = binding.containedButton
        button.setOnClickListener {
            var validacion = true
            var radioRes = "Ninguno"
            var switchRes = "Si"
            var checkboxRes = "Aceptado"
            binding.txedUsuario.setHelperTextColor(binding.root.context.getColorStateList(R.color.red_600))
            binding.txedUsuario.setHelperText("")
            if (binding.edUsuario.text.toString() == ""){
                Toast.makeText(binding.root.context,"Campos sin llenar", Toast.LENGTH_LONG).show()
                binding.txedUsuario.setHelperText("Campo sin llenar");
                validacion = false
            }

            binding.txedPassword.setHelperTextColor(binding.root.context.getColorStateList(R.color.red_600))
            binding.txedPassword.setHelperText("")
            if (binding.edPassword.text.toString() == ""){
                binding.txedPassword.setHelperText("Campo sin llenar");
                validacion = false
            }

            binding.txedNombre.setHelperTextColor(binding.root.context.getColorStateList(R.color.red_600))
            binding.txedNombre.setHelperText("")
            if (binding.edNombre.text.toString().isEmpty()){
                binding.txedNombre.setHelperText("Campo sin llenar");
                validacion = false
            }

            binding.txedApellido.setHelperTextColor(binding.root.context.getColorStateList(R.color.red_600))
            binding.txedApellido.setHelperText("")
            if (binding.edApellido.text.toString().isEmpty()){
                binding.txedApellido.setHelperText("Campo sin llenar");
                validacion = false
            }

            binding.txedDireccion.setHelperTextColor(binding.root.context.getColorStateList(R.color.red_600))
            binding.txedDireccion.setHelperText("")
            if (binding.edDireccion.text.toString().isEmpty()){
                binding.txedDireccion.setHelperText("Campo sin llenar");
                validacion = false
            }

            binding.txedTelefono.setHelperTextColor(binding.root.context.getColorStateList(R.color.red_600))
            binding.txedTelefono.setHelperText("")
            if (binding.edTelefono.text.toString().isEmpty()){
                binding.txedTelefono.setHelperText("Campo sin llenar");
                validacion = false
            }

            if (!validacion)
                return@setOnClickListener

            val cliente = Cliente()
            cliente.nombres = binding.edNombre.text.toString()
            cliente.apellidos = binding.edApellido.text.toString()
            cliente.direccion = binding.edDireccion.text.toString()
            cliente.telefono = binding.edTelefono.text.toString()
            cliente.correo = binding.edUsuario.text.toString()
            cliente.contraseña = binding.edPassword.text.toString()
            ws.insertCliente(cliente)
            val texto = "Usuario: ${binding.edUsuario.text}\nContraseña: ${binding.edPassword.text}\nGenero: ${radioRes}\nMayor de edad?: $switchRes\nAceptó terminos?: $checkboxRes"
            Aplicacion.resultado = texto
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val button2 = binding.outlinedButton
        button2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        return
    }
}