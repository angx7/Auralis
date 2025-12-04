package com.example.cameratest.ui.screens.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.ui.screens.components.AuralisScreenHeader
import com.example.cameratest.ui.screens.sessions.components.PrecisionChartCard
import com.example.cameratest.ui.screens.sessions.components.ProgressMetricsRow
import com.example.cameratest.ui.theme.CameraTestTheme

@Composable
fun ProgressScreen(innerPadding: PaddingValues, navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(innerPadding)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        AuralisScreenHeader(
            title = "Mi Progreso",
            onBackClick = { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrecisionChartCard()

        Spacer(modifier = Modifier.height(16.dp))

        // Las 3 métricas de abajo
        ProgressMetricsRow()
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