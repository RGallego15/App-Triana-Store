package com.example.trianastore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trianastore.registro.registrarActivity
import com.example.trianastore.registro.registroActivity
import com.example.trianastore.registro.lista_usuariosActivity
import com.example.trianastore.R

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {
            startActivity(Intent(this, registroActivity::class.java))
        }

        val btn5 = findViewById<Button>(R.id.btn5)
        btn5.setOnClickListener {
            startActivity(Intent(this, registrarActivity::class.java))
        }

        val btnFacebook = findViewById<ImageView>(R.id.btnFacebook)
        btnFacebook.setOnClickListener {
            val url = "https://www.facebook.com"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        val btnWhatsApp = findViewById<ImageView>(R.id.btnWhatsApp)
        btnWhatsApp.setOnClickListener {
            val url = "https://wa.me/51987654321" // <-- cambia por tu número con código de país
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        val btnTikTok = findViewById<ImageView>(R.id.btnTikTok)
        btnTikTok.setOnClickListener {
            val url = "https://www.tiktok.com/@tu_usuario" // <-- cambia por tu enlace de perfil
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        val btnVerMapa: Button = findViewById(R.id.btnVerMapa)
        btnVerMapa.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

    }
}
