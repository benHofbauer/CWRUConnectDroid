package com.example.cwruconnectdroid.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.math.min

suspend fun processProfilePhoto(context: Context, uri: Uri): String? {
    return withContext(Dispatchers.IO) {
        try {
            // 1. Decode URI to Bitmap
            val inputStream = context.contentResolver.openInputStream(uri)
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            if (originalBitmap == null) return@withContext null

            // 2. Center-Crop to a 1:1 Square
            val width = originalBitmap.width
            val height = originalBitmap.height
            val size = min(width, height)
            val x = (width - size) / 2
            val y = (height - size) / 2

            val squaredBitmap = Bitmap.createBitmap(originalBitmap, x, y, size, size)

            // 3. Scale precisely to 128x128
            val targetSize = 128
            val scaledBitmap = Bitmap.createScaledBitmap(squaredBitmap, targetSize, targetSize, true)

            // Clean up intermediate bitmaps to free up memory
            if (squaredBitmap != originalBitmap) originalBitmap.recycle()
            if (scaledBitmap != squaredBitmap) squaredBitmap.recycle()

            // 4. Compress and Encode to Base64
            val outputStream = ByteArrayOutputStream()

            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
            val byteArray = outputStream.toByteArray()

            // Base64.NO_WRAP is recommended for APIs to avoid adding line breaks
            val base64String = Base64.encodeToString(byteArray, Base64.NO_WRAP)

            scaledBitmap.recycle()

            base64String
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}