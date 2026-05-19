package com.fic.mobile_app_base_compose.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fic.mobile_app_base_compose.data.local.BioBaseDatos
import com.fic.mobile_app_base_compose.data.model.Hallazgo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/**
 * Heredamos de 'AndroidViewModel' en lugar de 'ViewModel' estándar,
 * para poder usar el parámetro 'application' y dárselo a Room de forma directa y fácil.
 */
class HallazgoViewModel(application: Application) : AndroidViewModel(application) {

    // Inicializamos la base de datos y extraemos el lápiz de comandos (DAO)
    private val baseDatos = BioBaseDatos.obtenerInstancia(application)
    private val comandosDao = baseDatos.objetoAccesoDatos()

    /**
     * 'listaHallazgos' es un contenedor de lectura constante.
     * 'stateIn' transforma el Flow del DAO en un estado que Jetpack Compose puede pintar en tiempo real.
     */
    val listaHallazgos: StateFlow<List<Hallazgo>> = comandosDao.obtenerHallazgos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    // Estado para la lista de hallazgos (Bitácora)
    private val _hallazgos = MutableStateFlow<List<Hallazgo>>(emptyList())
    val hallazgos: StateFlow<List<Hallazgo>> = _hallazgos.asStateFlow()

    // Cajas de texto reactivas (Almacenan temporalmente lo que el usuario escribe)
    var titulo = MutableStateFlow("")
    var descripcion = MutableStateFlow("")


    fun agregarHallazgo() {

        if (titulo.value.isBlank() || descripcion.value.isBlank()) {
            return // Le avisa a la pantalla que la validación falló
        }
        /**
         * viewModelScope.launch: Abre una corrutina (un hilo secundario).
         * Almacenar datos en el disco duro toma tiempo y esto evita que Android muestre un error de congelamiento.
         */
        viewModelScope.launch {
            val hallazgoParaGuardar = Hallazgo(
                titulo = titulo.value,
                descripcion = descripcion.value
            )

            // Enviamos el objeto al DAO de Room para escribirlo en SQLite
            comandosDao.guardar(hallazgoParaGuardar)

            // Limpieza absoluta de las cajas para dejarlas listas en la siguiente captura
            titulo.value = ""
            titulo.value = ""
        }

    }
}