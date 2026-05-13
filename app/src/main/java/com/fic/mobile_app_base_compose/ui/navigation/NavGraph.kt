package com.fic.mobile_app_base_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fic.mobile_app_base_compose.ui.screens.CatalogScreen
import com.fic.mobile_app_base_compose.ui.screens.FormScreen
import com.fic.mobile_app_base_compose.ui.screens.ListScreen


sealed class Screen(val route: String) {
    object List : Screen("list_screen")
    object Form : Screen(route = "form_screen")
    object Catalog : Screen(route = "catalog_screen")
}

@Composable
fun BioNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.List.route) {

        composable(Screen.List.route) {
            ListScreen(navController)
        }

        composable(Screen.Form.route) {
            FormScreen(navController)
        }

        composable(Screen.Catalog.route) {
            CatalogScreen(navController)
        }
    }
}