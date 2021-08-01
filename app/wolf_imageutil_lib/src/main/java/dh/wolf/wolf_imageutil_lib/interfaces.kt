package dh.wolf.wolf_imageutil_lib

import android.graphics.Bitmap
import org.jetbrains.annotations.NotNull

interface IWolfBitmap {

    // bitmap compress
    fun bitmapCompress(@NotNull bitmap: Bitmap, format: Bitmap.CompressFormat, compressionRate : Int = 90): Bitmap?

    // get byte array from bitmap
    fun bitmapToByteArray(@NotNull bitmap: Bitmap, format: Bitmap.CompressFormat, compressionRate: Int = 90): ByteArray

    // get resize bitmap
    fun bitmapRatioResize(@NotNull image: Bitmap, maxWidth: Int = 1024, maxHeight: Int = 1024): Bitmap

    // get rotate bitmap
    fun bitmapRotate(@NotNull bitmap: Bitmap, degrees: Int): Bitmap?

    // get forward bitmap
    fun bitmapForward(@NotNull bitmap: Bitmap, @NotNull filepath: String): Bitmap?
}