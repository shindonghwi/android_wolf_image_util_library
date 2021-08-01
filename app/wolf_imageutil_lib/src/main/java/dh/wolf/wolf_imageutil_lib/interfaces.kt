package dh.wolf.wolf_imageutil_lib

import android.content.res.Resources
import android.graphics.Bitmap
import android.widget.ImageView
import org.jetbrains.annotations.NotNull

interface IWolfImage {
    fun compressBitmap(@NotNull bitmap: Bitmap, format: Bitmap.CompressFormat, compressionRate : Int = 90): Bitmap?
    fun compressBitmap(@NotNull imageView: ImageView, format: Bitmap.CompressFormat, compressionRate : Int = 90): Bitmap?
    fun compressBitmap(@NotNull drawable: Int, resourses: Resources, format: Bitmap.CompressFormat, compressionRate : Int = 90): Bitmap?
}