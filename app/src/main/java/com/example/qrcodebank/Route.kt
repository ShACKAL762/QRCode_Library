package com.example.qrcodebank

sealed class Route(val route:String) {
    object Camera:Route("Camera")
    object Library:Route("Library")
}

