package com.fic.mobile_app_base_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fic.mobile_app_base_compose.ui.navigation.BioNavHost
import com.fic.mobile_app_base_compose.ui.theme.MobileappbasecomposeTheme

/**
 * MainActivity: El punto de entrada principal de la aplicación Android.
 * Hereda de ComponentActivity, que es la base para actividades que usan Jetpack Compose.
 */
class MainActivity : ComponentActivity() {

    /**
     * onCreate es el primer método que se ejecuta al iniciar la aplicación.
     * Aquí se prepara la interfaz y los componentes lógicos.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. CONFIGURACIÓN DE PANTALLA COMPLETA
        // Habilita que el contenido se dibuje detrás de las barras de estado y navegación
        // (Edge-to-Edge), permitiendo una experiencia visual moderna.
        enableEdgeToEdge()

        // 2. DEFINICIÓN DEL CONTENIDO (Compose)
        // setContent define la jerarquía de la interfaz de usuario.
        setContent {
            // Aplicamos el tema visual de la aplicación (colores, tipografía y formas)
            MobileappbasecomposeTheme {

                // 3. SUPERFICIE BASE (Surface)
                // Actúa como el lienzo principal. Usamos el color de fondo definido en el tema.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 4. INICIALIZACIÓN DEL CONTROLADOR DE NAVEGACIÓN
                    // 'rememberNavController' crea y guarda el estado del navegador.
                    // Este objeto se encarga de cambiar entre pantallas (List, Form, Catalog).
                    val navController = rememberNavController()

                    // 5. LANZAMIENTO DEL HOST DE NAVEGACIÓN
                    // Llamamos a nuestro NavHost personalizado (BioNavHost), pasándole el controlador.
                    // Aquí es donde se decide qué pantalla se muestra primero.
                    BioNavHost(navController = navController)
                }
            }
        }
    }
}