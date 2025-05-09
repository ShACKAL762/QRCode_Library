package com.example.qrcodebank.ui.camera.viewModel

import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrcodebank.domain.camera.CameraUseCase
import kotlinx.coroutines.launch

class CameraViewModel(
    private val cameraUseCase: CameraUseCase
) : ViewModel(
) {
    private val liveDataStateHolder = MutableLiveData<CameraScreenState>()
    val liveDataState = liveDataStateHolder

    fun startScan(lifecycleOwner: LifecycleOwner, preview: Preview) {
        val callbackImpl = object : MyCallback {
            override fun onComplete(result: String) {
                println("Callback received: $result")
                liveDataState.value = CameraScreenState.Result(result)
            }
        }

        viewModelScope.launch {
            cameraUseCase.scanCode(lifecycleOwner, preview,callbackImpl)
        }

    }
}
interface MyCallback {
    fun onComplete(result: String)
}