package com.example.qrcodebank.ui.camera

import android.view.ViewGroup
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.qrcodebank.ui.camera.viewModel.startCamera
import kotlinx.coroutines.launch

class CameraScreen {

    @Composable
    fun CameraView(
    ) {
        val coroutineScope = rememberCoroutineScope()
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        val cameraProviderFuture =
            remember(context) { ProcessCameraProvider.getInstance(context) }
        val executor = remember(context) { ContextCompat.getMainExecutor(context) }

        val previewView = PreviewView(context).apply {
            this.scaleType = PreviewView.ScaleType.FILL_CENTER
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        Column {
            AndroidView(
                modifier = Modifier.fillMaxHeight(0.5f),
                factory = { _ ->
                    cameraProviderFuture.addListener(Runnable {

                        val previewUseCase = Preview.Builder()
                            .build()
                            .also {
                                it.surfaceProvider = previewView.surfaceProvider
                            }
                        coroutineScope.launch {
                            val cameraProvider = cameraProviderFuture.get()
                            startCamera(cameraProvider, lifecycleOwner, previewUseCase,executor,context)
                        }
                    }, executor)


                    previewView
                }
            )
        }
    }
}