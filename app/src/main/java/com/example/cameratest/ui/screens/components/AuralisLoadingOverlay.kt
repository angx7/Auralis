package com.example.cameratest.ui.screens.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.cameratest.ui.theme.Disc

@Composable
fun AuralisLoadingOverlay() {
    val colors = MaterialTheme.colorScheme
    val infinite = rememberInfiniteTransition()

    val rotation by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                imageVector = Disc, // Usa un disco musical
                contentDescription = "Loading",
                modifier = Modifier
                    .size(90.dp)
                    .graphicsLayer() { rotationZ = rotation },
                tint = colors.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Loading...",
                style = MaterialTheme.typography.titleMedium,
                color = colors.onSurface
            )
        }
    }
}
