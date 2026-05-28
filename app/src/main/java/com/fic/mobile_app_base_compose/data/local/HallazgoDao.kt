package com.fic.mobile_app_base_compose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    /**
     * Modifica los datos de un registro existente en la base de datos.
     * Room utiliza la llave primaria (ID) del objeto para identificar cuál registro modificar.
     */
    @Update
    suspend fun modificar(hallazgo: Hallazgo)

    /**
     * Elimina un registro específico de la base de datos.
     * Al igual que actualizar, requiere que el objeto tenga su ID definido para encontrarlo en la tabla.
     */
    @Delete
    suspend fun borrar(hallazgo: Hallazgo)
}