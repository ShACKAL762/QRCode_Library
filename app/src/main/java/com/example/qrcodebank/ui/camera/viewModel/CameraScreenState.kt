package com.example.qrcodebank.ui.camera.viewModel

sealed interface CameraScreenState {
    data object Default : CameraScreenState
    data object Error : CameraScreenState
    data class Result(val result: String):CameraScreenState


}
