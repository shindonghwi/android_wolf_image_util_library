package dh.wolf.wolf_imageutil_lib

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
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

    // get bitmap string
    fun bitmapToString(@NotNull bitmap: Bitmap, format: Bitmap.CompressFormat, compressionRate: Int = 90): String
}

interface IWolfGallery{
    // 갤러리로 부터 이미지 가져오기
    fun getImageFromGallery(@NotNull activity: Activity, requestCode: Int)

    // 갤러리로 부터 다중 이미지 가져오기
    fun getMultiImageFromGallery(@NotNull activity: Activity, requestCode: Int)

    // 갤러리로 부터 비디오 가져오기
    fun getVideoFromGallery(@NotNull activity: Activity, requestCode: Int)

    // 갤러리에서 가져온 파일의 실제경로
    fun getFileRealPath(@NotNull context: Context, @NotNull fileUri: Uri): String

    // 비디오의 재생시간 가져오기
    fun getVideoPlayTime(path: String): HashMap<String, Int>
}