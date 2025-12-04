package com.example.cameratest.ui.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.cameratest.ui.theme.DarkColors
import com.example.cameratest.ui.theme.PrimaryGradientDark
import com.example.cameratest.ui.theme.PrimaryGradientLight

@Composable
fun AuthActionButton(
    title: String,
    onClick : () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val isDark = colorScheme == DarkColors
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = if (isDark) PrimaryGradientDark else PrimaryGradientLight
                )
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = colorScheme.onPrimary
        )
    }
}