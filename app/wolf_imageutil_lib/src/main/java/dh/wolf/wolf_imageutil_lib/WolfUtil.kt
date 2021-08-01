package dh.wolf.wolf_imageutil_lib

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.SystemClock
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log
import java.io.*

open class WolfUtil : IWolfBitmap, IWolfGallery {

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

    private var lastOpenTime: Long = 0

    override fun getImageFromGallery(activity: Activity, requestCode: Int) {
        if (SystemClock.elapsedRealtime() - lastOpenTime < 300L) {
            return
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            activity.startActivityForResult(intent, requestCode)
        }
        lastOpenTime = SystemClock.elapsedRealtime()
    }

    override fun getVideoFromGallery(activity: Activity, requestCode: Int) {
        if (SystemClock.elapsedRealtime() - lastOpenTime < 300L) {
            return
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "video/*"
            intent.action = Intent.ACTION_GET_CONTENT
            activity.startActivityForResult(intent, requestCode)
        }
        lastOpenTime = SystemClock.elapsedRealtime()
    }

    @SuppressLint("Recycle")
    override fun getFileRealPath(context: Context, fileUri: Uri): String {
        val returnCursor: Cursor = context.contentResolver.query(fileUri, null, null, null, null)!!
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        val file = File(context.filesDir, name)
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(fileUri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable: Int = inputStream!!.available()
            val bufferSize = Math.min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream.read(buffers).also { read = it } != -1) {
                outputStream.write(buffers, 0, read)
            }
            inputStream.close()
            outputStream.close()
            Log.e("File Path", "Path " + file.absolutePath)
        } catch (e: java.lang.Exception) {
            Log.e("Exception", e.message!!)
        }
        return file.absolutePath
    }

    override fun getVideoPlayTime(path: String): HashMap<String, Int> {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(path)

        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        val timeInMilliSec = time!!.toLong()
        val duration = timeInMilliSec / 1000
        val hours = duration / 3600
        val minutes = (duration - hours * 3600) / 60
        val seconds = duration - (hours * 3600 + minutes * 60)

        val timeJsonData = HashMap<String, Int>()
        timeJsonData["hour"] = hours.toInt()
        timeJsonData["minute"] = minutes.toInt()
        timeJsonData["second"] = seconds.toInt()
        return timeJsonData
    }
}