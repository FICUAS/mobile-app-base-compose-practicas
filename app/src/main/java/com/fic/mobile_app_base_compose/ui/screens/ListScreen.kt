package com.fic.mobile_app_base_compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.fic.mobile_app_base_compose.R
import com.fic.mobile_app_base_compose.ui.navigation.Screen
import com.fic.mobile_app_base_compose.ui.components.BottomMenuBar
import com.fic.mobile_app_base_compose.viewmodel.HallazgoViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight

/**
 * Pantalla principal que muestra los hallazgos registrados por el usuario.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavHostController, viewModel: HallazgoViewModel) {

    /**
     * collectAsState: Escucha los datos reales de la base de datos Room.
     * Si un nuevo elemento es agregado desde el formulario, esta pantalla se redibuja sola automáticamente.
     */
    val listadoHallazgos by viewModel.listaHallazgos.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.titulo_bitacora)) }
            )
        },
        bottomBar = { BottomMenuBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.Form.route) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.agregar_descripcion)
                )
            }
        }
    ) { valoresMargen ->

        // Condicional Lógica: Si el arreglo está vacío, pintamos el mensaje central
        if (listadoHallazgos.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(valoresMargen)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.lista_vacia),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            /**
             * LazyColumn: Es la lista optimizada nativa. Reemplaza por completo al antiguo RecyclerView de XML.
             * Solo procesa los elementos visibles en pantalla ahorrando memoria RAM.
             */
            LazyColumn(modifier = Modifier.padding(valoresMargen)) {
                items(listadoHallazgos) { elemento ->
                    ListItem(
                        headlineContent = {
                            Text(text = elemento.titulo, fontWeight = FontWeight.Bold)
                        },
                        supportingContent = {
                            Text(text = elemento.descripcion)
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(
                            horizontal = dimensionResource(R.dimen.margen_medio)
                        )
                    )
                }
            }
        }
    }
}