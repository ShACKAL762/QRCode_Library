package com.example.qrcodebank.data.camera

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.example.qrcodebank.ui.camera.viewModel.MyCallback
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage


@SuppressLint("CommitPrefEdits")
@OptIn(ExperimentalGetImage::class)
fun processImageProxy(
    barcodeScanner: BarcodeScanner,
    imageProxy: ImageProxy,
    context: Context,
    result: MyCallback,
) {

    val inputImage =
        InputImage.fromMediaImage(
            imageProxy.image!!,
            imageProxy.imageInfo.rotationDegrees
        )
    val pref = context.getSharedPreferences("TEST", MODE_PRIVATE).edit()
    var text: String
    barcodeScanner.process(inputImage)
        .addOnSuccessListener { barcodes ->
            barcodes.forEach { barcode ->

                val rawValue = barcode.rawValue
                text = rawValue.toString()
                println("Предварительно $text ")
                result.onComplete(text)
                val valueType = barcode.valueType
                pref.putString("QR",rawValue)
                when (valueType) {

                    Barcode.TYPE_WIFI -> {
                        val ssid = barcode.wifi!!.ssid
                        val password = barcode.wifi!!.password
                        val type = barcode.wifi!!.encryptionType
                        text =
                            "ssid: " + ssid + "\npassword: " + password + "\ntype: " + type
                    }

                    Barcode.TYPE_URL -> {
                        val title = barcode.url!!.title
                        val url = barcode.url!!.url
                        text = "Title: " + title + "\nURL: " + url
                    }

                }
            }
        }
        .addOnFailureListener {
            Log.e(TAG, it.message ?: it.toString())
        }
        .addOnCompleteListener {
            imageProxy.close()
        }


}