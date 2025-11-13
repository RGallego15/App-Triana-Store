package com.example.trianastore.registro


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trianastore.R
import com.example.trianastore.registro.licenciasActivity
import com.example.trianastore.registro.productosActivity
import com.example.trianastore.registro.SoporteActivity
class registroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn2 = findViewById<Button>(R.id.btn2)
        val gosoporte = Intent (this, SoporteActivity::class.java)
        btn2.setOnClickListener{startActivity(gosoporte)}

        val btn3 = findViewById<Button>(R.id.btn3)
        val golicencias = Intent (this, licenciasActivity::class.java)
        btn3.setOnClickListener{startActivity(golicencias)}

        val btn4 = findViewById<Button>(R.id.btn4)
        val goproductos = Intent (this, productosActivity::class.java)
        btn4.setOnClickListener{startActivity(goproductos)}
    }
}