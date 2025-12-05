package com.example.cameratest.ui.screens.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.ui.screens.components.AuralisLoadingOverlay
import com.example.cameratest.ui.screens.components.AuralisScreenHeader
import com.example.cameratest.ui.screens.sessions.components.MonthSummaryCard
import com.example.cameratest.ui.screens.sessions.components.SessionHistoryCard
import com.example.cameratest.ui.theme.CameraTestTheme
import com.example.cameratest.ui.viewmodels.HistoryViewModel


// =============== SCREEN ===================

@Composable
fun HistoryScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val colorScheme = MaterialTheme.colorScheme
    val historyViewModel: HistoryViewModel = viewModel()
    val uiState by historyViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        historyViewModel.loadHistory()
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
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            // HEADER REUTILIZABLE
            AuralisScreenHeader(
                title = "Historial de Sesiones",
                onBackClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // RESUMEN DEL MES
            MonthSummaryCard(
                totalTime = uiState.monthTotalTime,
                avgPrecision = uiState.monthAvgPrecision,
                sessionsCount = uiState.monthSessionsCount
            )

            Spacer(modifier = Modifier.height(16.dp))

            // LISTA DE SESIONES
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(uiState.sessions) { session ->
                    SessionHistoryCard(session = session)
                }
            }
        }

        if (uiState.isLoading) {
            AuralisLoadingOverlay()
        }
    }
}

// =============== PREVIEWS ===================

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreviewLight() {
    CameraTestTheme(darkTheme = false) {
        HistoryScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreviewDark() {
    CameraTestTheme(darkTheme = true) {
        HistoryScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}
