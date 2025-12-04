package com.example.loginmodernkotlin

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VentasActivity : AppCompatActivity() {

    private lateinit var listaVentas: ArrayList<Venta>
    private lateinit var adapter: VentasAdapter
    private lateinit var db: VentasDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ventas)

        db = VentasDBHelper(this)

        val btnNuevaVenta = findViewById<Button>(R.id.btnNuevaVenta)
        val recycler = findViewById<RecyclerView>(R.id.recyclerVentas)

        listaVentas = db.obtenerVentas()

        adapter = VentasAdapter(listaVentas)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        btnNuevaVenta.setOnClickListener {
            mostrarDialogNuevaVenta()
        }
    }

    private fun mostrarDialogNuevaVenta() {

        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_nueva_venta, null)

        val etProducto = dialogView.findViewById<EditText>(R.id.etProducto)
        val etCantidad = dialogView.findViewById<EditText>(R.id.etCantidad)
        val etPrecio = dialogView.findViewById<EditText>(R.id.etPrecio)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Registrar Venta")
            .setView(dialogView)
            .setPositiveButton("Guardar", null)
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {

            val producto = etProducto.text.toString()
            val cantidadTxt = etCantidad.text.toString()
            val precioTxt = etPrecio.text.toString()

            if (producto.isEmpty() || cantidadTxt.isEmpty() || precioTxt.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cantidad = cantidadTxt.toInt()
            val precio = precioTxt.toDouble()

            // Crear venta sin ID (la BD lo genera)
            val nuevaVenta = Venta(
                producto = producto,
                cantidad = cantidad,
                precio = precio
            )

            val idInsert = db.insertarVenta(nuevaVenta)

            if (idInsert > 0) {

                // Crear venta con el ID real que devolvió SQLite
                val ventaConId = Venta(
                    id = idInsert.toInt(),
                    producto = producto,
                    cantidad = cantidad,
                    precio = precio
                )

                listaVentas.add(ventaConId)
                adapter.notifyItemInserted(listaVentas.size - 1)

                Toast.makeText(this, "Venta guardada", Toast.LENGTH_SHORT).show()
                dialog.dismiss()

            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }

    }
    }

