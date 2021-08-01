package dh.wolf.wolf_imageutil_lib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import android.content.res.Resources

/** 이미지 압축 */
open class ImageCompress: IWolfImage {

    override fun compressBitmap(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        compressionRate: Int
    ): Bitmap? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(format, compressionRate, stream)
        val byteArray: ByteArray = stream.toByteArray()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    override fun compressBitmap(
        imageView: ImageView,
        format: Bitmap.CompressFormat,
        compressionRate: Int
    ): Bitmap? {
        val drawable = imageView.drawable as BitmapDrawable
        val bitmap = drawable.bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(format, compressionRate, stream)
        val byteArray: ByteArray = stream.toByteArray()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    override fun compressBitmap(
        drawable: Int,
        format: Bitmap.CompressFormat,
        compressionRate: Int
    ): Bitmap? {
        val bitmap = BitmapFactory.decodeResource(Resources.getSystem(), drawable)
        val stream = ByteArrayOutputStream()
        bitmap.compress(format, compressionRate, stream)
        val byteArray: ByteArray = stream.toByteArray()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}