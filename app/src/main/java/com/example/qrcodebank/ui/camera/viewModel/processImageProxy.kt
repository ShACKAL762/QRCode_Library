package com.example.qrcodebank.ui.camera.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage


@OptIn(ExperimentalGetImage::class)
fun processImageProxy(
    barcodeScanner: BarcodeScanner,
    imageProxy: ImageProxy,
    result:(text:String?)-> Unit
): String {

    val inputImage =
        InputImage.fromMediaImage(
            imageProxy.image!!,
            imageProxy.imageInfo.rotationDegrees)
    var text = ""

    barcodeScanner.process(inputImage)
        .addOnSuccessListener { barcodes ->
            barcodes.forEach { barcode ->

                val rawValue = barcode.rawValue
                text = rawValue.toString()
                println("Предварительно $text ")
                result.invoke(text)
                val valueType = barcode.valueType
                when (valueType) {
                    Barcode.TYPE_WIFI -> {
                        val ssid = barcode.wifi!!.ssid
                        val password = barcode.wifi!!.password
                        val type = barcode.wifi!!.encryptionType
                         text =
                            "ssid: " + ssid + "\npassword: " + password + "\ntype: " + type
                        println("wifi $text ")
                    }
                    Barcode.TYPE_URL -> {
                        val title = barcode.url!!.title
                        val url = barcode.url!!.url
                        text = "Title: " + title + "\nURL: " + url
                            // println("url $text ")
                    }

                }
            }
        }
        .addOnFailureListener {
            Log.e(TAG, it.message ?: it.toString())
        }
        .addOnCompleteListener {
            //Once the image being analyzed
            //closed it by calling ImageProxy.close()
            imageProxy.close()
        }
    return text
}