package com.example.loginmodernkotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VentasDBHelper(context: Context) :
    SQLiteOpenHelper(context, "ventas.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE ventas(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "producto TEXT NOT NULL," +
                    "cantidad INTEGER NOT NULL," +
                    "precio REAL NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ventas")
        onCreate(db)
    }

    // Insertar venta
    fun insertarVenta(venta: Venta): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("producto", venta.producto)
            put("cantidad", venta.cantidad)
            put("precio", venta.precio)
        }
        return db.insert("ventas", null, values)
    }

    // Obtener todas las ventas
    fun obtenerVentas(): ArrayList<Venta> {
        val lista = ArrayList<Venta>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ventas", null)

        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Venta(
                        id = cursor.getInt(0),
                        producto = cursor.getString(1),
                        cantidad = cursor.getInt(2),
                        precio = cursor.getDouble(3)
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }
}
