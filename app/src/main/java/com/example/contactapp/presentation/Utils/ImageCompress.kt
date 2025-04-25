package com.example.contactapp.presentation.Utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream


fun ImageCompress(imageData: ByteArray): ByteArray {
    var bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
    var outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
    return outputStream.toByteArray()
}
