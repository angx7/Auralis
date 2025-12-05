package com.example.cameratest.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.navigation.EditUserScreenRoute
import com.example.cameratest.navigation.HistoryScreenRoute
import com.example.cameratest.navigation.LoginScreenRoute
import com.example.cameratest.navigation.PracticeScreenRoute
import com.example.cameratest.navigation.ProgressScreenRoute
import com.example.cameratest.ui.screens.home.components.AiTipCard
import com.example.cameratest.ui.screens.home.components.HomeHeader
import com.example.cameratest.ui.screens.home.components.HomeSquareButton
import com.example.cameratest.ui.screens.home.components.RealTimeSessionCard
import com.example.cameratest.ui.theme.Bar_chart_4_bars
import com.example.cameratest.ui.theme.CameraTestTheme
import com.example.cameratest.ui.theme.History
import com.example.cameratest.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val colorScheme = MaterialTheme.colorScheme
    val homeViewModel: HomeViewModel = viewModel()
    val username by homeViewModel.username.collectAsState(initial = "")

    // Estado del drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Opciones",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )

                NavigationDrawerItem(
                    label = { Text("Editar usuario") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(EditUserScreenRoute)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Editar usuario"
                        )
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text("Cerrar sesión") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(LoginScreenRoute) {
                            homeViewModel.logout()
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                            contentDescription = "Cerrar sesión"
                        )
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                // ================= HEADER =================
                HomeHeader(
                    userName = username,
                    streakText = "Tu racha actual: 4 días tocando.",
                    onSettingsClick = {
                        scope.launch { drawerState.open() }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ====== BOTÓN GRANDE "Iniciar Sesión en Tiempo Real" ======
                RealTimeSessionCard(
                    onClick = {
                        navController.navigate(PracticeScreenRoute)
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ====== AI PRO TIP DEL DÍA ======
                AiTipCard(
                    tipTitle = "AI Pro Tip del Día:",
                    tipBody = if (homeViewModel.tipText.isNotEmpty()) homeViewModel.tipText else "Cargando tip...",
                    onClick = { homeViewModel.loadProTip() }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ====== DOS BOTONES: MI PROGRESO / HISTORIAL ======
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HomeSquareButton(
                        icon = Bar_chart_4_bars,
                        label = "Mi Progreso",
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate(ProgressScreenRoute)
                    }

                    HomeSquareButton(
                        icon = History,
                        label = "Historial",
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate(HistoryScreenRoute)
                    }
                }
            }
        }
    }
}

// ================= PREVIEWS ===================

@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewLight() {
    CameraTestTheme(darkTheme = false) {
        HomeScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewDark() {
    CameraTestTheme(darkTheme = true) {
        HomeScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}
