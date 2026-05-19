package com.fic.mobile_app_base_compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.fic.mobile_app_base_compose.R
import com.fic.mobile_app_base_compose.viewmodel.HallazgoViewModel

/**
 * Pantalla de formulario para capturar un nuevo hallazgo.
 * No incluye menú inferior para evitar que el usuario pierda su progreso.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(navController: NavHostController, viewModel: HallazgoViewModel) {

    val titulo by viewModel.titulo.collectAsState()
    val descripcion by viewModel.descripcion.collectAsState()


    /**
     * VARIABLES DE INTERRUPTOR LOCAL:
     * Almacenan temporalmente si la caja de texto debe pintarse en estado de error (color rojo).
     */
    var tituloObligatorio by remember { mutableStateOf(false) }
    var descripcionObligatoria by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.titulo_nuevo_registro)) },
                // Botón para volver atrás en la pila de navegación
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.boton_regresar)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Layout vertical con espaciado uniforme
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(dimensionResource(R.dimen.margen_grande)) // Margen de 24.dp desde dimens
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.espaciado_medio))
        ) {
            // Campo de texto para el título
            OutlinedTextField(
                value = titulo,
                onValueChange = { nuevoTexto ->
                    viewModel.titulo.value = nuevoTexto
                    // En cuanto se empiece a escribir de nuevo, apagamos el error rojo automáticamente
                    if (nuevoTexto.isNotBlank()) tituloObligatorio = false
                },
                label = { Text(stringResource(R.string.etiqueta_titulo)) },
                modifier = Modifier.fillMaxWidth(),
                isError = tituloObligatorio,
                supportingText = {
                    // Si hay un error, dibuja dinámicamente un texto de advertencia debajo de la caja
                    if (tituloObligatorio) {
                        Text(text = stringResource(R.string.error_titulo_vacio))
                    }
                }
            )

            // Campo de texto multilínea para descripción
            OutlinedTextField(
                value = descripcion,
                onValueChange = { nuevoTexto ->
                    viewModel.descripcion.value = nuevoTexto
                    // En cuanto se escriba en la descripción, apagamos su aviso rojo
                    if (nuevoTexto.isNotBlank()) descripcionObligatoria = false
                }, label = { Text(stringResource(R.string.etiqueta_descripcion)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                isError = descripcionObligatoria, // Si es true, la caja se vuelve roja
                supportingText = {
                    // Texto de advertencia inferior para la descripción
                    if (descripcionObligatoria) {
                        Text(text = stringResource(R.string.error_descripcion_vacia))
                    }
                }
            )

            // Espaciador flexible que empuja el botón al final de la pantalla
            Spacer(modifier = Modifier.weight(1f))

            // Botón de guardar (Temporalmente solo cierra la pantalla)
            Button(
                onClick = {
                    val tituloVacio = titulo.isBlank()
                    val descripcionVacia = descripcion.isBlank()

                    tituloObligatorio = tituloVacio
                    descripcionObligatoria = descripcionVacia

                    if (!tituloVacio && !descripcionVacia) {
                        viewModel.agregarHallazgo()
                        navController.popBackStack()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.altura_boton_estandar))
            ) {
                Text(stringResource(R.string.boton_guardar))
            }
        }
    }
}