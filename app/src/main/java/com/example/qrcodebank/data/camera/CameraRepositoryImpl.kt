package com.example.qrcodebank.data.camera

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner
import com.example.qrcodebank.domain.camera.CameraRepository
import com.example.qrcodebank.ui.camera.viewModel.MyCallback


class CameraRepositoryImpl(
    private val context: Context,
) : CameraRepository {

    override fun scanCode(lifecycleOwner: LifecycleOwner, preview: Preview, result: MyCallback) {
            startCamera(
                context,
                lifecycleOwner,
                preview,
                result
            )
        }

    override fun getResult() : String{
        val pref = context.getSharedPreferences("TEST",MODE_PRIVATE)
        val result = pref.getString("QR","Ошибка")
        return result?:""
    }
}
