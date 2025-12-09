package com.example.cameratest.ui.screens.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner

import com.example.cameratest.ui.viewmodels.CameraProcessor
import com.example.cameratest.ui.viewmodels.PianoView

@Composable
fun CameraScreen() {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // ---------------------------
    // Permisos
    // ---------------------------
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasCameraPermission = granted
        }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (!hasCameraPermission) {
        return
    }

    // ---------------------------
    // Vistas reales
    // ---------------------------
    val previewView = remember { PreviewView(context) }
    val pianoView = remember { PianoView(context) }
    val pianoDetector = remember { PianoDetector() }

    // Procesador que une todo
    val cameraProcessor = remember {
        CameraProcessor(
            context = context,
            previewView = previewView,
            pianoView = pianoView,
            pianoDetector = pianoDetector
        )
    }

    // Iniciar detector de manos
    LaunchedEffect(Unit) {
        cameraProcessor.setupHandDetector()
        cameraProcessor.startCamera()
    }

    // ---------------------------
    // UI
    // ---------------------------
    Box(modifier = Modifier.fillMaxSize()) {

        // 1) Cámara
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { previewView }
        )

        // 2) Vista del piano encima
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { pianoView }
        )
    }
}
