package com.example.qrcodebank.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.qrcodebank.ui.main.MainScreen
import com.example.qrcodebank.ui.theme.QRCodeBankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRCodeBankTheme {
                MainScreen()
            }
        }
    }
}
