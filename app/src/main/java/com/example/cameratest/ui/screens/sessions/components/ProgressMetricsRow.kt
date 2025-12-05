// ui/screens/sessions/components/ProgressMetricsRow.kt
package com.example.cameratest.ui.screens.sessions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cameratest.ui.theme.Bullseye
import com.example.cameratest.ui.theme.Soundwave
import com.example.cameratest.ui.theme.TrendingUp
import kotlin.math.roundToInt

@Composable
fun ProgressMetricsRow(
    ritmoNivel: String,
    dinamicaPuntaje: Double?,
    precisionGlobal: Double?
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProgressMetricCard(
            icon = Soundwave,
            title = "Ritmo Estable",
            subtitle = if (ritmoNivel.isNotBlank())
                "Nivel ${ritmoNivel.replaceFirstChar { it.uppercase() }}"
            else
                "Sin datos",
            modifier = Modifier.weight(1f)
        )
        ProgressMetricCard(
            icon = TrendingUp,
            title = "Dinámica",
            subtitle = dinamicaPuntaje?.let {
                "${it.roundToInt()}/100"
            } ?: "Sin datos",
            modifier = Modifier.weight(1f)
        )
        ProgressMetricCard(
            icon = Bullseye,
            title = "Precisión",
            subtitle = precisionGlobal?.let {
                "${it.roundToInt()}%"
            } ?: "Sin datos",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ProgressMetricCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    Card(
        modifier = modifier,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.primary.copy(alpha = 0.08f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = colorScheme.primary.copy(alpha = 0.18f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}