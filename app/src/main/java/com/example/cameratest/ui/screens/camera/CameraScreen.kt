package com.example.cameratest.ui.screens.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cameratest.ui.viewmodels.CameraViewModel
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark

@Composable
fun CameraScreen(viewModel: CameraViewModel = viewModel()) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val landmarks by viewModel.landmarks.collectAsState()
    val fingerCounts by viewModel.fingerCounts.collectAsState()

    val permissions = arrayOf(Manifest.permission.CAMERA)
    var hasPermissions by remember {
        mutableStateOf(
            permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        )
    }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        hasPermissions = permissionsMap.values.all { it }
    }

    LaunchedEffect(Unit) {
        if (!hasPermissions) {
            launcher.launch(permissions)
        }
    }

    val preview = remember { Preview.Builder().build() }
    val imageAnalysis = remember {
        ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(ContextCompat.getMainExecutor(context), viewModel)
            }
    }

    LaunchedEffect(hasPermissions) {
        if (hasPermissions) {
            bindCamera(context, lifecycleOwner, preview, imageAnalysis)
        }
    }

    Box(Modifier.fillMaxSize()) {
        if (hasPermissions) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    previewView
                }
            )

            LandmarksOverlay(
                modifier = Modifier.fillMaxSize(),
                hands = landmarks
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (fingerCounts.isEmpty()) {
                    Text("Sin manos detectadas", color = Color.White)
                } else {
                    fingerCounts.forEachIndexed { index, count ->
                        Text("Mano ${index + 1}: $count dedos extendidos", color = Color.White)
                    }
                }
            }
        } else {
            Text("Se necesita permiso de cámara", Modifier.align(Alignment.Center))
        }
    }
}

private fun bindCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    preview: Preview,
    analysis: ImageAnalysis
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                analysis
            )
        } catch (exc: Exception) {
            Log.e("CameraBind", "Fallo al vincular casos de uso", exc)
        }
    }, ContextCompat.getMainExecutor(context))
}

@Composable
fun LandmarksOverlay(modifier: Modifier, hands: List<List<NormalizedLandmark>>) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height
        val colors = listOf(Color.Cyan, Color.Magenta)
        hands.forEachIndexed { index, hand ->
            val color = colors[index % colors.size]
            hand.forEach {
                drawCircle(
                    color = color,
                    radius = 4f,
                    center = Offset(it.x() * w, it.y() * h)
                )
            }
        }
    }
}
