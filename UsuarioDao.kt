package com.example.trianastore.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insertar(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contraseña = :contraseña")
    suspend fun login(correo: String, contraseña: String): Usuario?

    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodos(): List<Usuario>
}
