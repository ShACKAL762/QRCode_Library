package com.example.qrcodebank.domain.camera

import android.content.Context
import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner
import com.example.qrcodebank.ui.camera.viewModel.MyCallback
import kotlinx.coroutines.flow.Flow

interface CameraRepository {
    fun scanCode(lifecycleOwner: LifecycleOwner, preview: Preview, result: MyCallback)
    fun getResult():String
}