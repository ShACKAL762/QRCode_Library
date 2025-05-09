package com.example.qrcodebank.data.camera

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.qrcodebank.ui.camera.viewModel.MyCallback
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode


fun startCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewUseCase: Preview,
    result: MyCallback
) {

    val cameraProviderFuture =
        ProcessCameraProvider.getInstance(context)
    val cameraProvider =
        cameraProviderFuture.get()
    val executor = ContextCompat.getMainExecutor(context)

    //Test сканирования qrCode
    //Настройка настроек сканера
    val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient(options)

    val imageAnalysis = ImageAnalysis.Builder()
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

    imageAnalysis.setAnalyzer(
        executor
    ) { image: ImageProxy ->
        processImageProxy(barcodeScanner, image,context,result)
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