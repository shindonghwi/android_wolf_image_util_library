package dh.wolf.wolf_imageutil_lib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

object WolfImageUtil : IWolfImage {

    /** 이미지 압축 */
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
}

