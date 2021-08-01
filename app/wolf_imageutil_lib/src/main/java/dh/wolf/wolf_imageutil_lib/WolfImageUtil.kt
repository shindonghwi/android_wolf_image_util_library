package dh.wolf.wolf_imageutil_lib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import java.io.ByteArrayOutputStream

class ToastTest{
    companion object{
        fun showToast(context: Context, msg: String) = Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}

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

