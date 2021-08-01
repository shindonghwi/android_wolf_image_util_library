WolfImageUtil
=====

WolfImageUtil가 제공하는 기능은 아래와 같다.
1. 비트맵 압축
2. 비트맵 byteArray 가져오기
3. 비트맵 비율에 맞게 리사이즈
4. 비트맵 회전
5. 정방향 비트맵 가져오기

------------------------------------------------------

Download
--------

### Gradle
```
[ project - build.gradle ]
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
or 
```
[ setting.gradle ]
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}
```

------------------------------------------------------

## How To Use

#### 1. 비트맵을 압축하는 기능
```
// return -> Bitmap
WolfImageUtil.bitmapCompress(
    bitmap = /** bitmap */,
    format = /** Bitmap.CompressFormat.PNG or Bitmap.CompressFormat.JPEG */,
    compressionRate = /** value -> 0 until 100 */
)
```

#### 2. 비트맵 byteArray 가져오기
```
// return -> Bitmap
WolfImageUtil.bitmapToByteArray(
    bitmap = /** bitmap */,
    format = /** Bitmap.CompressFormat.PNG or Bitmap.CompressFormat.JPEG */,
    compressionRate = /** value -> 0 until 100 */
)
```

#### 3. 비트맵 비율에 맞게 리사이즈
```
// default 가로 최대 크기 : 1024
// default 세로 최대 크기 : 1024
// return -> Bitmap
WolfImageUtil.bitmapRatioResize(
    bitmap = /** bitmap */,
    maxWidth: Int = 1024,
    maxHeight: Int = 1024
)
```

#### 4. 비트맵 회전
```
// return -> Bitmap
WolfImageUtil.bitmapRotate(
    bitmap = /** bitmap */,
    degrees: Int /** 회전 값 */
)
```

#### 5. 정방향 비트맵 가져오기
```
// 갤러리에서 이미지를 가져온 후 서버에 업로드를하게 되면 이미지가 회전되어 있는 현상이 발생합니다.
// 그래서 이미지를 정방향으로 변경해주는 함수 입니다.
// return -> Bitmap
WolfImageUtil.bitmapForward(
    bitmap = /** bitmap */,
    filepath = /** 이미지 파일의 경로 */
)
```
