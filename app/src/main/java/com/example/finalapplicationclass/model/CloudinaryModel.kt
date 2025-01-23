package com.example.finalapplicationclass.model

import android.content.Context
import android.graphics.Bitmap
import com.example.finalapplicationclass.BuildConfig
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.cloudinary.android.policy.GlobalUploadPolicy
import com.example.finalapplicationclass.base.MyApplication
import com.example.finalapplicationclass.model.utlls.extentions.toFile
import java.io.File
import java.io.FileOutputStream
import java.lang.Error
import kotlin.io.path.CopyActionContext

class CloudinaryModel {

    init {
        val config = mapOf(
            "cloud_name" to BuildConfig.CLOUD_NAME,
            "api_key" to BuildConfig.API_KEY,
            "api_secret" to BuildConfig.API_SECRET
        )

        MyApplication.Globals.context?.let {
            MediaManager.init(it, config)
            MediaManager.get().globalUploadPolicy = GlobalUploadPolicy.defaultPolicy()
        }
    }

    fun uploadImage(
        bitmap: Bitmap,
        name: String,
        onSuppress: (String?) -> Unit,
        onError: (String?) -> Unit
    ) {
        val context = MyApplication.Globals.context ?: return
        val file: File = bitmap.toFile(context, name)

        MediaManager.get().upload(file.path)
            .option("folder", "image")
            .callback(object : UploadCallback {
                override fun onStart(requestId: String?) {
                }

                override fun onProgress(
                    requestId: String?,
                    bytes: Long,
                    totalBytes: Long
                ) {
                }

                override fun onSuccess(requestId: String?, resultData: Map<*, *>?) {
                    val url = resultData?.get("secure_url") as? String ?: ""

                    if (url.isNotEmpty()) {
                        onSuccess(url, resultData = null) // Pass both URL and resultData
                    } else {
                        onError("Secure URL is missing or empty") // Handle error
                    }
                }

                override fun onError(
                    requestId: String?,
                    error: ErrorInfo?
                ) {
                    onError(error?.description ?: "Unknown error")
                }

                override fun onReschedule(
                    requestId: String?,
                    error: ErrorInfo?
                ) {
                }
            })
            .dispatch()
    }
}

