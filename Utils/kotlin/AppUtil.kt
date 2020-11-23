package com.example.utils

import android.app.ActivityManager
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.example.YTApp
import java.io.File
import java.security.MessageDigest


class AppUtil private constructor() {

    init {
        throw Error("Do not need instantiate!")
    }

    companion object {

        // private const val DEBUG = true
        // private const val TAG = "AppUtil"

        /**
         * 获取 App 版本号
         *
         * @param context 上下文
         * @return 当前版本 Code
         */
        @Suppress("DEPRECATION")
        fun getVerCode(context: Context): Int {
            var verCode = -1
            try {
                val packageName = context.packageName
                // verCode = context.packageManager.getPackageInfo(packageName, 0).versionCode
                verCode = context.packageManager.getPackageInfo(
                    packageName, PackageManager.GET_ACTIVITIES
                ).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodError) {
                e.printStackTrace()
            }

            return verCode
        }


        /**
         * 获取文件存储的路径
         *
         * @param context 上下文对象
         * @return path
         */
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        fun getApkPath(context: Context): String {
            val directoryPath =
                if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
                    // 判断外部存储是否能用
                    context.getExternalFilesDir("").absolutePath
                } else {
                    context.filesDir.toString() + File.separator
                }

            val file = File(directoryPath)
            if (!file.exists()) {
                file.mkdirs()
            }

            return directoryPath
        }


        /**
         * 获取应用运行的最大内存
         *
         * @return 最大内存
         */
        val maxMemory: Long
            get() = Runtime.getRuntime().maxMemory() / 1024


        /**
         * 得到软件显示版本信息
         *
         * @param context 上下文
         * @return 当前版本信息
         */
        fun getVerName(context: Context): String {
            var verName = ""
            try {
                val packageName = context.packageName
                verName = context.packageManager.getPackageInfo(packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verName
        }


        /**
         * 获取应用签名
         *
         * @param context 上下文
         * @param pkgName 包名
         * @return 返回应用的签名
         */
        @RequiresApi(Build.VERSION_CODES.P)
        fun getSign(context: Context, pkgName: String): String? {
            return try {
                val pis = context.packageManager
                    .getPackageInfo(
                        pkgName,
                        // PackageManager.GET_SIGNATURES
                        PackageManager.GET_SIGNING_CERTIFICATES
                    )
                // hexDigest(pis.signatures[0].toByteArray())
                hexDigest(pis.signingInfo.apkContentsSigners[0].toByteArray())
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                null
            }

        }

        /**
         * 将签名字符串转换成需要的 32 位签名
         *
         * @param paramArrayOfByte 签名 byte 数组
         * @return 32 位签名字符串
         */
        private fun hexDigest(paramArrayOfByte: ByteArray): String {
            val hexDigits = charArrayOf(
                48.toChar(),
                49.toChar(),
                50.toChar(),
                51.toChar(),
                52.toChar(),
                53.toChar(),
                54.toChar(),
                55.toChar(),
                56.toChar(),
                57.toChar(),
                97.toChar(),
                98.toChar(),
                99.toChar(),
                100.toChar(),
                101.toChar(),
                102.toChar()
            )
            try {
                val localMessageDigest = MessageDigest.getInstance("MD5")
                localMessageDigest.update(paramArrayOfByte)
                val arrayOfByte = localMessageDigest.digest()
                val arrayOfChar = CharArray(32)
                var i = 0
                var j = 0
                while (true) {
                    if (i >= 16) {
                        return String(arrayOfChar)
                    }
                    val k = arrayOfByte[i].toInt()
                    arrayOfChar[j] = hexDigits[0xF and k.ushr(4)]
                    arrayOfChar[++j] = hexDigits[k and 0xF]
                    i++
                    j++
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }


        /**
         * 获取设备的可用内存大小
         *
         * @param context 应用上下文对象 context
         * @return 当前内存大小
         */
        fun getDeviceUsableMemory(context: Context): Int {
            val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val mi = ActivityManager.MemoryInfo()
            am.getMemoryInfo(mi)
            // 返回当前系统的可用内存
            return (mi.availMem / (1024 * 1024)).toInt()
        }

        /**
         * 获取手机型号
         */
        fun getMobileModel(): String {
            var model: String? = Build.MODEL
            model = model?.trim { it <= ' ' } ?: ""
            return model
        }

        /**
         * 获取手机系统 SDK 版本
         *
         * @return 如API 23 则返回 23
         */
        val sdkVersion: Int
            get() = Build.VERSION.SDK_INT


        /**
         * 下载 APK 文件
         *
         * @param context
         * @param title
         * @param fileUrl
         *
         * @return
         */
        fun downloadApk(context: Context, title: String, fileUrl: String): Long {
            val request = DownloadManager.Request(Uri.parse(fileUrl))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            request.setDestinationInExternalFilesDir(
                context,
                Environment.DIRECTORY_DOWNLOADS,
                "CoalYinTai.apk"
            )
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            // 设置 Notification 信息
            request.setTitle(title)
            request.setDescription("下载完成后请点击打开")
            request.setVisibleInDownloadsUi(true)
            request.allowScanningByMediaScanner()
            request.setMimeType("application/vnd.android.package-archive")

            // 实例化 DownloadManager 对象
            val downloadManager =
                YTApp.context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            return downloadManager.enqueue(request)
        }
    }
}
