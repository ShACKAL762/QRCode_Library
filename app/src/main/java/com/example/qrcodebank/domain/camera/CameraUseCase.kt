package com.example.qrcodebank.domain.camera

import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner
import com.example.qrcodebank.ui.camera.viewModel.MyCallback

interface CameraUseCase {
    fun scanCode(lifecycleOwner: LifecycleOwner, preview: Preview, result: MyCallback)
    //fun getResult():String
}