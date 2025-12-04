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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.ui.screens.components.AuralisScreenHeader
import com.example.cameratest.ui.screens.sessions.components.PracticeFiltersRow
import com.example.cameratest.ui.screens.sessions.components.PracticeItemCard
import com.example.cameratest.ui.theme.CameraTestTheme

// ======================= MODELOS =======================

enum class PracticeDifficulty { ALL, EASY, MEDIUM, HARD }

data class PracticePiece(
    val id: Int,
    val title: String,
    val composer: String,
    val difficulty: PracticeDifficulty,
    val duration: String,
    val coverUrl: String? = null
)


// ======================= SCREEN =======================

@Composable
fun PracticeSelectionScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val colorScheme = MaterialTheme.colorScheme

    // Datos de ejemplo (luego esto va a venir de la API)
    val allPieces = remember {
        listOf(
            PracticePiece(1, "Claro de Luna", "Beethoven", PracticeDifficulty.EASY, "3:45", "https://auralismusic.s3.us-east-1.amazonaws.com/Images/bethoveen_virus.png"),
            PracticePiece(2, "Turkish March", "Mozart", PracticeDifficulty.MEDIUM, "2:30", "https://auralismusic.s3.us-east-1.amazonaws.com/Images/bethoveen_virus.png"),
            PracticePiece(3, "Fantaisie-Impromptu", "Chopin", PracticeDifficulty.HARD, "5:15", "https://auralismusic.s3.us-east-1.amazonaws.com/Images/bethoveen_virus.png"),
            PracticePiece(4, "Marion March", "Chopin - Mozart", PracticeDifficulty.EASY, "1:00", "https://auralismusic.s3.us-east-1.amazonaws.com/Images/bethoveen_virus.png"),
            PracticePiece(5, "Miessinga der Right", "Chopin - Mozart", PracticeDifficulty.MEDIUM, "2:30", "https://auralismusic.s3.us-east-1.amazonaws.com/Images/bethoveen_virus.png"),
            PracticePiece(6, "Even a World", "Chopin - Lahoro", PracticeDifficulty.EASY, "3:17", "https://auralismusic.s3.us-east-1.amazonaws.com/Images/bethoveen_virus.png"),
            PracticePiece(7, "Turkish, March", "Mozart", PracticeDifficulty.MEDIUM, "2:30", "https://auralismusic.s3.us-east-1.amazonaws.com/Images/bethoveen_virus.png"),
        )
    }

    var selectedFilter by remember { mutableStateOf(PracticeDifficulty.ALL) }

    val filteredPieces = remember(selectedFilter, allPieces) {
        if (selectedFilter == PracticeDifficulty.ALL) allPieces
        else allPieces.filter { it.difficulty == selectedFilter }
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
