package com.example.qrcodebank.ui.camera.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import java.util.concurrent.Executor


fun startCamera(
    cameraProvider: ProcessCameraProvider,
    lifecycleOwner: LifecycleOwner,
    previewUseCase: Preview,
    executor: Executor,
    context: Context
) {
    //Test сканирования qrCode
    //Настройка настроек сканера
    val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient(options)

    val imageAnalysis = ImageAnalysis.Builder()
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()
        .also {
            it.setAnalyzer(
                executor
            ) { image: ImageProxy ->
                processImageProxy(barcodeScanner, image) { result ->
                    cameraProvider.unbindAll()
                Toast.makeText(context,result,Toast.LENGTH_LONG).show()
                }
            }
        }


    try {
        cameraProvider.unbindAll()
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        cameraProvider.bindToLifecycle(
            lifecycleOwner, cameraSelector, previewUseCase, imageAnalysis

        )

    } catch (ex: Exception) {
        Log.e("CameraPreview", "Use case binding failed", ex)
    }

}

fun stopCamera() {


}