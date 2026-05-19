package com.fic.mobile_app_base_compose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Anotación @Entity: Le dice a Android que use esta clase como molde
 * para crear una tabla llamada "hallazgos" en la base de datos.
 */
@Entity(tableName = "hallazgos")
data class Hallazgo(
    /**
     * @PrimaryKey: Define el identificador único de cada renglón.
     * autoGenerate = true: Android se encarga de numerar (1, 2, 3...) de forma automática.
     */
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descripcion: String,
    val categoria: String = "Otro",
    val rutaFoto: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0,
    val fechaRegistro: Long = System.currentTimeMillis()
)
