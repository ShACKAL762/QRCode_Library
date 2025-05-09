package com.example.qrcodebank.domain.camera

import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner
import com.example.qrcodebank.ui.camera.viewModel.MyCallback

class CameraUseCaseImpl(private val cameraRepository: CameraRepository) :CameraUseCase{
    override fun scanCode(
        lifecycleOwner: LifecycleOwner,
        preview: Preview,
        result: MyCallback
    ) {
        return cameraRepository.scanCode(lifecycleOwner,preview,result)
    }

    //override fun getResult(): String {
    //    return cameraRepository.getResult()
    //}
}