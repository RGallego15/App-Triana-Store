package com.example.trianastore.registro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.trianastore.R
import com.example.trianastore.data.BaseDatos
import com.example.trianastore.data.Usuario
import com.example.trianastore.registro.lista_usuariosActivity
import kotlinx.coroutines.launch

class registrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        val btnVerUsuarios = findViewById<Button>(R.id.btnVerUsuarios)
        btnVerUsuarios.setOnClickListener {
            val intent = Intent(this, lista_usuariosActivity::class.java)
            startActivity(intent)
        }
        // Enlazamos los campos con los IDs del XML
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreo)
        val txtContraseña = findViewById<EditText>(R.id.txtContraseña)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        // Obtenemos la instancia de la base de datos
        val db = BaseDatos.getDatabase(this)



        // Acción del botón
        btnRegistrar.setOnClickListener {
            val nombre = txtNombre.text.toString().trim()
            val correo = txtCorreo.text.toString().trim()
            val contraseña = txtContraseña.text.toString().trim()

            // Validamos que no haya campos vacíos
            if (nombre.isNotEmpty() && correo.isNotEmpty() && contraseña.isNotEmpty()) {

                // Insertamos en la base de datos usando corrutina
                lifecycleScope.launch {
                    val usuario = Usuario(nombre = nombre, correo = correo, contraseña = contraseña)
                    db.usuarioDao().insertar(usuario)

                    // Mostramos confirmación en el hilo principal
                    runOnUiThread {
                        Toast.makeText(
                            this@registrarActivity,
                            "Usuario $nombre registrado con éxito ✅",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Limpiar campos
                        txtNombre.text.clear()
                        txtCorreo.text.clear()
                        txtContraseña.text.clear()
                    }
                }

            } else {
                Toast.makeText(this, "Por favor, completa todos los campos ⚠️", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
