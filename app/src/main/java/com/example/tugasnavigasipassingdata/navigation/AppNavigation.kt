package com.example.tugasnavigasipassingdata.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugasnavigasipassingdata.tampilan.screens.HomeScreen
import com.example.tugasnavigasipassingdata.tampilan.screens.DetailScreen
import com.example.tugasnavigasipassingdata.tampilan.screens.ProfileScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Daftar item untuk Bottom Navigation
    val navItems = listOf(
        Pair("Home", Icons.Default.Home),
        Pair("Profile", Icons.Default.Person)
    )
    val navRoutes = listOf(Screen.HOME, Screen.PROFILE)

    Scaffold(
        bottomBar = {
            NavigationBar {
                // Dapatkan rute saat ini untuk indikator aktif
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                navItems.forEachIndexed { index, item ->
                    val route = navRoutes[index]
                    NavigationBarItem(
                        icon = { Icon(item.second, contentDescription = item.first) },
                        label = { Text(item.first) },
                        // --- IMPLEMENTASI INDIKATOR AKTIF ---
                        selected = currentDestination?.hierarchy?.any { it.route == route } == true,
                        onClick = {
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Rute untuk Screen A (Sumber)
            composable(Screen.HOME) {
                HomeScreen(navController = navController)
            }

            // Rute untuk Screen Tambahan
            composable(Screen.PROFILE) {
                ProfileScreen()
            }

            // Rute untuk Screen B (Tujuan)
            composable(
                route = Screen.DETAIL,
                arguments = listOf(navArgument("itemJson") { type = NavType.StringType })
            ) { backStackEntry ->
                // Ambil argumen JSON
                val itemJson = backStackEntry.arguments?.getString("itemJson")

                // --- PERBAIKAN BUG '+' ---
                val decodedJson = itemJson?.let {
                    URLDecoder.decode(it, StandardCharsets.UTF_8.name()) // <- Tambahkan .name()
                }

                // Panggil DetailScreen dengan parameter baru
                DetailScreen(
                    itemJson = decodedJson,
                    onNavigateBack = {
                        navController.popBackStack() // Ini adalah fungsi untuk "Kembali"
                    }
                )
            }
        }
    }
}