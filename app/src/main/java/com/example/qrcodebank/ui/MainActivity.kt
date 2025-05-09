package com.example.qrcodebank.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import com.example.qrcodebank.Navigation
import com.example.qrcodebank.ui.camera.CameraScreen
import com.example.qrcodebank.ui.camera.viewModel.CameraViewModel
import com.example.qrcodebank.ui.main.MainScreen
import com.example.qrcodebank.ui.main.MainViewModel
import com.example.qrcodebank.ui.theme.QRCodeBankTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRCodeBankTheme {
                MainScreen().Main()
            }
        }
    }
}
