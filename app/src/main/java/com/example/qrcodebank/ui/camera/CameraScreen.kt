package com.example.qrcodebank.ui.camera

import android.R.attr.label
import android.R.attr.text
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.qrcodebank.ui.camera.viewModel.CameraScreenState
import com.example.qrcodebank.ui.camera.viewModel.CameraViewModel
import org.koin.androidx.compose.koinViewModel


class CameraScreen {
    @Composable
    fun CameraView(viewModel: CameraViewModel = koinViewModel()) {
        val localClipboardManager = LocalClipboardManager.current

        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        val result = remember { mutableStateOf("sample") }


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
                    val preview = Preview.Builder()
                        .build()
                        .also {
                            it.surfaceProvider = previewView.surfaceProvider
                        }
                    viewModel.startScan(lifecycleOwner, preview)

                    previewView
                }
            )
            SelectionContainer (
                Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .clickable {
                        localClipboardManager.setText(AnnotatedString(result.value))
                    Toast.makeText(context, "Сохранил в буфер обмена", Toast.LENGTH_SHORT).show()
                    }
            ) {
                Text(
                    text = result.value,
                    textAlign = TextAlign.Center
                )
            }
        }
        viewModel.liveDataState.observe(lifecycleOwner) {
            when (it) {
                CameraScreenState.Default -> TODO()
                CameraScreenState.Error -> Toast.makeText(context, "ERROR EBAT", Toast.LENGTH_SHORT)
                    .show()

                is CameraScreenState.Result -> {
                    println(it.result)
                    result.value = it.result
                }
            }
        }
    }
}