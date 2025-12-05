package com.example.cameratest.ui.screens.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.ui.screens.components.AuralisLoadingOverlay
import com.example.cameratest.ui.screens.components.AuralisScreenHeader
import com.example.cameratest.ui.screens.sessions.components.PracticeFiltersRow
import com.example.cameratest.ui.screens.sessions.components.PracticeItemCard
import com.example.cameratest.ui.theme.CameraTestTheme
import com.example.cameratest.ui.viewmodels.SongViewModel

// ======================= MODELOS =======================

enum class PracticeDifficulty { ALL, EASY, MEDIUM, HARD }

// ======================= SCREEN =======================

@Composable
fun PracticeSelectionScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val colorScheme = MaterialTheme.colorScheme
    val songViewModel: SongViewModel = viewModel()
    val allPieces = songViewModel.allPieces
    val isLoading = songViewModel.isLoading
    val errorMessage = songViewModel.errorMessage
    var selectedFilter by remember { mutableStateOf(PracticeDifficulty.ALL) }

    LaunchedEffect(Unit) {
        songViewModel.loadSongs()
    }

    val filteredPieces = remember(selectedFilter, allPieces) {
        if (selectedFilter == PracticeDifficulty.ALL) {
            allPieces
        } else {
            allPieces.filter { it.difficulty == selectedFilter }
        }
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

            // HEADER
            AuralisScreenHeader(
                title = "Seleccionar Práctica",
                onBackClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // FILTERS
            PracticeFiltersRow(
                selected = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // LISTA
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(filteredPieces) { piece ->
                    PracticeItemCard(
                        piece = piece,
                        onClick = {
                            // TODO: navegar al detalle / sesión con esta pieza
                        }
                    )
                }
            }
        }
    }

    if (isLoading) {
        AuralisLoadingOverlay()
    }
}

// ================= PREVIEWS =================

@Preview(showBackground = true)
@Composable
fun PracticeSelectionPreviewLight() {
    CameraTestTheme(darkTheme = false) {
        PracticeSelectionScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PracticeSelectionPreviewDark() {
    CameraTestTheme(darkTheme = true) {
        PracticeSelectionScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}