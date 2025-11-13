package com.example.trianastore.registro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import android.widget.ListView
import com.example.trianastore.R
import com.example.trianastore.data.BaseDatos
import com.example.trianastore.registro.UsuarioAdapter
import kotlinx.coroutines.launch

class lista_usuariosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_usuarios)

        val listView = findViewById<ListView>(R.id.listaUsuarios)
        val db = BaseDatos.getDatabase(this)

               lifecycleScope.launch {
            val usuarios = db.usuarioDao().obtenerTodos()
                   if (usuarios.isNotEmpty()) {
                       val adapter = UsuarioAdapter(this@lista_usuariosActivity, usuarios)
                       listView.adapter = adapter
                   }

            }
        }
    }


