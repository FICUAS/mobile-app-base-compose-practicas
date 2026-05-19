package com.fic.mobile_app_base_compose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fic.mobile_app_base_compose.data.model.Hallazgo
import kotlinx.coroutines.flow.Flow

/**
 * Anotación @Dao: Objeto de Acceso a Datos (Data Access Object).
 * Es un contrato o "menú de comandos" con las consultas SQL que la app puede ejecutar.
 */
@Dao
interface HallazgoDao {

    /**
     * Inserta un nuevo registro completo dentro de la tabla de SQLite.
     * 'suspend' significa que se ejecutará en segundo plano para no trabar la pantalla.
     */
    @Insert
    suspend fun guardar(nuevoHallazgo: Hallazgo)

    /**
     * Consulta SQL para obtener toda la lista ordenada cronológicamente.
     * Usamos 'Flow' para que la interfaz se refresque sola en cuanto se guarde algo nuevo.
     */
    @Query("SELECT * FROM hallazgos ORDER BY fechaRegistro DESC")
    fun obtenerHallazgos(): Flow<List<Hallazgo>>
}