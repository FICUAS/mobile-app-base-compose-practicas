package com.fic.mobile_app_base_compose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fic.mobile_app_base_compose.data.model.Hallazgo

/**
 * @Database: Define qué tablas incluye la base de datos y la versión del archivo.
 */
@Database(entities = [Hallazgo::class], version = 1, exportSchema = false)
abstract class BioBaseDatos: RoomDatabase(){

    // Expone las funciones del DAO que creamos anteriormente
    abstract fun objetoAccesoDatos(): HallazgoDao

    companion object {
        // @Volatile asegura que la base de datos sea visible por cualquier hilo del procesador
        @Volatile
        private var INSTANCIA: BioBaseDatos? = null

        /**
         * Patrón Singleton simplificado: Evita abrir el archivo múltiples veces de forma simultánea,
         * lo cual corrompería los datos del teléfono.
         */
        fun obtenerInstancia(contexto: Context): BioBaseDatos {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    BioBaseDatos::class.java,
                    "biobitacora.db"
                ).build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}