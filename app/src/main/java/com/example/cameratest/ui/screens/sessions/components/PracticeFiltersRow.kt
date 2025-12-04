package com.example.cameratest.ui.screens.sessions.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cameratest.ui.screens.sessions.PracticeDifficulty

@Composable
fun PracticeFiltersRow(
    selected: PracticeDifficulty,
    onFilterSelected: (PracticeDifficulty) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PracticeFilterChip(
            text = "Todas",
            selected = selected == PracticeDifficulty.ALL,
            onClick = { onFilterSelected(PracticeDifficulty.ALL) },
            modifier = Modifier.weight(1f)
        )
        PracticeFilterChip(
            text = "Fácil",
            selected = selected == PracticeDifficulty.EASY,
            onClick = { onFilterSelected(PracticeDifficulty.EASY) },
            modifier = Modifier.weight(1f)
        )
        PracticeFilterChip(
            text = "Medio",
            selected = selected == PracticeDifficulty.MEDIUM,
            onClick = { onFilterSelected(PracticeDifficulty.MEDIUM) },
            modifier = Modifier.weight(1f)
        )
        PracticeFilterChip(
            text = "Difícil",
            selected = selected == PracticeDifficulty.HARD,
            onClick = { onFilterSelected(PracticeDifficulty.HARD) },
            modifier = Modifier.weight(1f)
        )
    }
}