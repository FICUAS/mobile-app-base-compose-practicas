package com.fic.mobile_app_base_compose.data.repository

import com.fic.mobile_app_base_compose.data.local.HallazgoDao
import com.fic.mobile_app_base_compose.data.model.Hallazgo

/**
 * Capa de Repositorio.
 * Su función es mediar entre el ViewModel y el DAO (la base de datos).
 * Esto permite que, si en el futuro decidimos cambiar SQLite por otra fuente de datos,
 * el resto de la aplicación no se rompa.
 */
class HallazgoRepository(private val dao: HallazgoDao) {

    // Exponemos el flujo de datos directamente para que sea observado por el ViewModel
    val listaHallazgos = dao.obtenerHallazgos()

    // Operación: CREATE
    suspend fun guardar(hallazgo: Hallazgo) = dao.guardar(hallazgo)

    // Operación: UPDATE
    // Permite modificar un registro existente sin tocar la lógica de la UI
    suspend fun modificar(hallazgo: Hallazgo) = dao.modificar(hallazgo)

    // Operación: DELETE
    // Permite eliminar un registro. Es vital para la gestión de datos del usuario
    suspend fun borrar(hallazgo: Hallazgo) = dao.borrar(hallazgo)
}