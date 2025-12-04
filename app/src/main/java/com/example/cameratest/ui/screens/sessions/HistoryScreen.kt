package com.example.cameratest.ui.screens.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.ui.screens.components.AuralisScreenHeader
import com.example.cameratest.ui.screens.sessions.components.MonthSummaryCard
import com.example.cameratest.ui.screens.sessions.components.SessionHistoryCard
import com.example.cameratest.ui.theme.CameraTestTheme

// =============== MODELO ===================

data class SessionHistoryItem(
    val id: Int,
    val day: String,        // "03"
    val monthShort: String, // "Dic"
    val time: String,       // "14:30"
    val title: String,      // "Sesión de Práctica: Beethoven - Claro de Luna"
    val duration: String,   // "45m"
    val precision: String,  // "92%"
    val iaScore: String     // "8.5/10"
)

// =============== SCREEN ===================

@Composable
fun HistoryScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val colorScheme = MaterialTheme.colorScheme

    // Datos mock (luego vendrán de la API)
    val monthSummary = remember {
        Triple("12h 45m", "88%", "24")
    }

    val sessions = remember {
        listOf(
            SessionHistoryItem(1, "03", "Dic", "14:30",
                "Sesión de Práctica: Beethoven - Claro de Luna",
                "45m", "92%", "8.5/10"
            ),
            SessionHistoryItem(2, "02", "Dic", "14:30",
                "Sesión de Práctica: Beethoven - Claro de Luna",
                "45m", "92%", "8.5/10"
            ),
            SessionHistoryItem(3, "01", "Dic", "14:30",
                "Sesión de Práctica: Beethoven - Claro de Luna",
                "45m", "92%", "8.5/10"
            ),
            SessionHistoryItem(4, "02", "Dic", "14:30",
                "Sesión de Práctica: Beethoven - Claro de Luna",
                "45m", "92%", "8.5/10"
            ),
            SessionHistoryItem(5, "03", "Dic", "14:30",
                "Sesión de Práctica: Beethoven - Claro de Luna",
                "45m", "92%", "8.5/10"
            )
        )
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
                totalTime = monthSummary.first,
                avgPrecision = monthSummary.second,
                sessionsCount = monthSummary.third
            )

            Spacer(modifier = Modifier.height(16.dp))

            // LISTA DE SESIONES
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(sessions) { session ->
                    SessionHistoryCard(session = session)
                }
            }
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
