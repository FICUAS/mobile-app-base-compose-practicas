package com.fic.mobile_app_base_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fic.mobile_app_base_compose.ui.screens.CatalogScreen
import com.fic.mobile_app_base_compose.ui.screens.FormScreen
import com.fic.mobile_app_base_compose.ui.screens.ListScreen
import com.fic.mobile_app_base_compose.viewmodel.HallazgoViewModel


sealed class Screen(val route: String) {
    object List : Screen("list_screen")
    object Form : Screen("form_screen?id={id}") {
        fun createRoute(id: Int = -1) = "form_screen?id=$id"
    }
    object Catalog : Screen(route = "catalog_screen")
}

@Composable
fun BioNavHost(navController: NavHostController,viewModel: HallazgoViewModel) {
    NavHost(navController = navController, startDestination = Screen.List.route) {

        composable(Screen.List.route) {
            ListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.Form.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            FormScreen(navController = navController, viewModel = viewModel, id = id)
        }

        composable(Screen.Catalog.route) {
            CatalogScreen(navController)
        }
    }
}