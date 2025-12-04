package com.example.loginmodernkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VentasAdapter(private val listaVentas: ArrayList<Venta>) :
    RecyclerView.Adapter<VentasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val etProducto: TextView = view.findViewById(R.id.tvProducto)
        val etCantidad: TextView = view.findViewById(R.id.tvCantidad)
        val etPrecio: TextView = view.findViewById(R.id.tvPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val venta = listaVentas[position]

        holder.etProducto.text = "Producto: ${venta.producto}"
        holder.etCantidad.text = "Cantidad: ${venta.cantidad}"
        holder.etPrecio.text = "Precio: S/. ${venta.precio}"
    }

    override fun getItemCount(): Int = listaVentas.size
}
