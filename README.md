WolfImageUtil
=====

WolfImageUtil가 제공하는 기능은 아래와 같다.
* 이미지 압축

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
WolfImageUtil.compressBitmap(
    /** [bitmap] or [imageView] or [drawable] */,
    format = /** Bitmap.CompressFormat.PNG or Bitmap.CompressFormat.JPEG */,
    compressionRate = /** value -> 0 until 100 */
)
```
