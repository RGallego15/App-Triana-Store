package com.example.trianastore.registro
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trianastore.R
import com.example.trianastore.data.Usuario

class UsuarioAdapter(private val context: Context, private val usuarios: List<Usuario>) : BaseAdapter() {

    override fun getCount(): Int = usuarios.size
    override fun getItem(position: Int): Any = usuarios[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.activity_item_usuario, parent, false)

        val usuario = usuarios[position]
        val txtNombre = view.findViewById<TextView>(R.id.txtNombre)
        val txtCorreo = view.findViewById<TextView>(R.id.txtCorreo)

        txtNombre.text = usuario.nombre
        txtCorreo.text = usuario.correo

        return view
    }
}