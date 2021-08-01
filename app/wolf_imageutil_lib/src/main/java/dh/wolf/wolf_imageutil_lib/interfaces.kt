package dh.wolf.wolf_imageutil_lib

import android.graphics.Bitmap
import org.jetbrains.annotations.NotNull

interface IWolfImage {
    fun compressBitmap(
        @NotNull bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        compressionRate : Int = 90
    ): Bitmap?
}