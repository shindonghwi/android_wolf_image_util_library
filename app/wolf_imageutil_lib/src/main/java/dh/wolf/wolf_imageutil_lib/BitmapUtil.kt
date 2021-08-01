package dh.wolf.wolf_imageutil_lib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import android.content.res.Resources
import android.graphics.Matrix
import android.media.ExifInterface
import android.util.Base64
import java.io.IOException

/** 비트맵 유틸 */
open class BitmapUtil : IWolfBitmap {

    override fun bitmapCompress(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        compressionRate: Int
    ): Bitmap? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(format, compressionRate, stream)
        val byteArray: ByteArray = stream.toByteArray()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    override fun bitmapToByteArray(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        compressionRate: Int
    ): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRate, stream)
        return stream.toByteArray()
    }

    override fun bitmapRatioResize(image: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val img = image
        return if (maxHeight > 0 && maxWidth > 0) {
            val width = image.width
            val height = image.height
            val ratioBitmap = width.toFloat() / height.toFloat()
            val ratioMax = maxWidth.toFloat() / maxHeight.toFloat()
            var finalWidth = maxWidth
            var finalHeight = maxHeight
            if (ratioMax > ratioBitmap) {
                finalWidth = (maxHeight.toFloat() * ratioBitmap).toInt()
            } else {
                finalHeight = (maxWidth.toFloat() / ratioBitmap).toInt()
            }
            Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true)
        } else {
            img
        }
    }

    override fun bitmapRotate(bitmap: Bitmap, degrees: Int): Bitmap? {
        if (degrees == 0) return bitmap
        val m = Matrix()
        m.setRotate(degrees.toFloat(), bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, m, true)
    }

    override fun bitmapForward(bitmap: Bitmap, filepath: String): Bitmap? {
        val exif: ExifInterface?
        try {
            exif = ExifInterface(filepath)
        } catch (e: IOException) {
            e.printStackTrace()
            return bitmap
        }
        val orientation: Int = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)
        if (orientation != -1) {
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> return bitmapRotate(bitmap, 90)
                ExifInterface.ORIENTATION_ROTATE_180 -> return bitmapRotate(bitmap, 180)
                ExifInterface.ORIENTATION_ROTATE_270 -> return bitmapRotate(bitmap, 270)
            }
        }
        return bitmapRotate(bitmap, 0)
    }

    override fun bitmapToString(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        compressionRate: Int
    ): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(format, compressionRate, stream)
        val bytes: ByteArray = stream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

}