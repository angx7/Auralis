package com.example.cameratest.models

import android.graphics.RectF

data class DetectedKey(
    val index: Int,
    val rect: RectF,
    val isBlack: Boolean
)
