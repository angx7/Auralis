package com.example.cameratest.ui.screens.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.ui.screens.components.AuralisLoadingOverlay
import com.example.cameratest.ui.screens.components.AuralisScreenHeader
import com.example.cameratest.ui.screens.sessions.components.PrecisionChartCard
import com.example.cameratest.ui.screens.sessions.components.ProgressMetricsRow
import com.example.cameratest.ui.theme.CameraTestTheme
import com.example.cameratest.ui.viewmodels.ProgressViewModel

@Composable
fun ProgressScreen(innerPadding: PaddingValues, navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()
    val viewModel: ProgressViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadReport()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            AuralisScreenHeader(
                title = "Mi Progreso",
                onBackClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrecisionChartCard(
                resumen = if (viewModel.resumen.isNotEmpty())
                    viewModel.resumen
                else
                    "No hay datos de progreso. ¡Comienza a practicar para ver tu progreso aquí!",
                chartValues = viewModel.graficaPrecision,
                precisionActual = viewModel.precisionActual
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProgressMetricsRow(
                ritmoNivel = viewModel.ritmoEstableNivel,
                dinamicaPuntaje = viewModel.dinamicaPuntaje,
                precisionGlobal = viewModel.precisionGlobal
            )
        }

        if (viewModel.isLoading) {
            AuralisLoadingOverlay()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressPreviewLight() {
    CameraTestTheme(darkTheme = false) {
        ProgressScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressPreviewDark() {
    CameraTestTheme(darkTheme = true) {
        ProgressScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}