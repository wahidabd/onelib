package com.wahidabd.library.utils.common

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Build.VERSION
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore.Images.Media
import androidx.fragment.app.Fragment
import com.esafirm.imagepicker.features.ImagePicker
import com.yalantis.ucrop.UCrop
import id.zelory.compressor.Compressor
import kotlinx.parcelize.Parcelize
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ImagePickerUtils {

    private val context = ContextProvider.get()
    private val packageName: String = context.packageName
    private val applicationName =
        context.applicationInfo.loadLabel(context.packageManager).toString()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }


    fun openCamera(activity: Activity, requestCode: Int) {
        ImagePicker.cameraOnly().imageDirectory(applicationName).start(activity, requestCode)
    }

    fun openCamera(fragment: Fragment, requestCode: Int) {
        ImagePicker.cameraOnly().imageDirectory(applicationName).start(fragment, requestCode)
    }

    fun openGallery(activity: Activity, requestCode: Int) {
        ImagePicker.create(activity).folderMode(true).single().showCamera(false).start(requestCode)
    }

    fun openGallery(fragment: Fragment, requestCode: Int) {
        ImagePicker.create(fragment).folderMode(true).single().showCamera(false).start(requestCode)
    }

    fun getOriginalImageFromGallery(data: Intent?): ResultImage {
        return if (data == null) {
            ResultImage(null, null)
        } else {
            val imagePicker = ImagePicker.getFirstImageOrNull(data)
            if (imagePicker == null) {
                ResultImage(null, null)
            } else {
                val path = imagePicker.path
                if (path.contains("file:/")) path.replace("file:/", "")

                val imageFile = File(path)
                if (imageFile.exists() && imageFile.isFile) ResultImage(
                    path,
                    imageFile
                ) else ResultImage(null, null)
            }

        }
    }

    fun getOriginalImage(data: Intent?): ResultImage {
        return if (VERSION.SDK_INT >= 29) {
            getOriginalImageForQ(data!!)
        } else {
            getOriginalImageBelowQ(data!!)
        }
    }

    fun getCompressedImage(data: Intent, quality: Int): ResultImage {
        return if (VERSION.SDK_INT >= 29) getCompressedImageForQ(data, quality)
        else getCompressedImageBelowQ(data, quality)
    }

    private fun getOriginalImageBelowQ(data: Intent): ResultImage {
        val imagePicker = ImagePicker.getFirstImageOrNull(data)
        return if (imagePicker == null) return ResultImage(null, null)
        else {
            val path = imagePicker.path
            if (path.contains("file:/")) path.replace("file:/", "")

            val currentFile = getImageDirectoryPath(getOldFileName(path))
            val newFile = getImageDirectoryPath(getNewFileName())
            renameImageFile(currentFile, newFile)

        }
    }

    private fun getOriginalImageForQ(data: Intent): ResultImage {
        val imagePicker = ImagePicker.getFirstImageOrNull(data)
        if (imagePicker == null) return ResultImage(null, null)
        else {
            val path = imagePicker.path
            if (path.contains("file:/")) path.replace("file:/", "")

            val newFileName = getNewFileName()
            val currentFile = getImageDirectoryPathForQ(getOldFileName(path))
            val newFile = getImageDirectoryPath(getNewFileName())

            return if (currentFile.exists() && currentFile.isFile) {
                renameAndMoveImageFileForQ(currentFile, newFile, newFileName)
            } else {
                val defaultPictureFile = File(
                    "" + Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                    ) + '/' + imagePicker.name
                )
                if (defaultPictureFile.exists()) renameAndMoveImageFileForQ(
                    defaultPictureFile,
                    newFile,
                    newFileName
                ) else renameAndMoveImageFileForQ(currentFile, newFile, newFileName)
            }
        }
    }

    private fun renameAndMoveImageFileForQ(
        currentFile: File,
        newFile: File,
        newFileName: String
    ): ResultImage {
        if (currentFile.exists() && currentFile.renameTo(newFile)) {
            val contentValues = setImageContentValues(newFileName)
            val uri = context.contentResolver.insert(Media.EXTERNAL_CONTENT_URI, contentValues)
            if (uri != null) {
                val outputStream = context.contentResolver.openOutputStream(uri)
                if (outputStream != null) {
                    val inputStream = FileInputStream(newFile)
                    inputStream.copyTo(outputStream)
                    outputStream.close()
                }

                contentValues.clear()
                contentValues.put("is_pending", 0)
                context.contentResolver.update(uri, contentValues, null, null)
            }

            currentFile.delete()
            return if (newFile.exists() && newFile.isFile) ResultImage(newFile.path, newFile)
            else ResultImage(null, null)
        } else {
            return ResultImage(null, null)
        }
    }

    private fun setImageContentValues(fileName: String): ContentValues {
        val contentValues = ContentValues()
        contentValues.put("_display_name", fileName)
        contentValues.put("title", fileName)
        contentValues.put("mime_type", "image/jpg")
        contentValues.put("is_pending", 1)
        contentValues.put("relative_path", Environment.DIRECTORY_PICTURES + '/' + applicationName)
        return contentValues
    }

    private fun getImageDirectoryPathForQ(imageName: String): File {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(dir?.absolutePath, "$applicationName/$imageName")
    }

    private fun getCompressedImageBelowQ(data: Intent, quality: Int): ResultImage {
        val imagePicker = ImagePicker.getFirstImageOrNull(data)
        return if (imagePicker == null) ResultImage(null, null)
        else {
            val path = imagePicker.path
            if (path.contains("file:/")) path.replace("file:/", "")

            val oldFileName = getOldFileName(path)
            val newFileName = getNewFileName()
            val currentFile = getImageDirectoryPathForQ(oldFileName)
            var compressedFile = currentFile

            try {
                compressedFile = compressImage(compressedFile.path, quality)
                val arr = arrayOfNulls<String>(1)
                arr[0] = currentFile.absolutePath
                deleteOldFile(arr)
            } catch (e: IOException) {
                e.printStackTrace()
                ResultImage(null, null)
            }

            renameCompressedImageFile(compressedFile, newFileName)
        }
    }

    private fun renameCompressedImageFile(currentFile: File, newFileName: String): ResultImage {
        val dataDir = Environment.getDataDirectory()

        return try {
            val compressImagePath = "/data/" + packageName + "/cache/images/" + currentFile.name
            val compressImageFile = File(dataDir, compressImagePath)
            val newImageFile = getImageDirectoryPath(newFileName)
            if (compressImageFile.exists()) {
                val compressedDataFileChannel = FileInputStream(compressImageFile).channel
                val newDataFileChannel = FileOutputStream(newImageFile).channel
                newDataFileChannel.transferFrom(
                    compressedDataFileChannel,
                    0L,
                    compressedDataFileChannel.size()
                )
                compressedDataFileChannel.close()
                newDataFileChannel.close()

                val arr = arrayOfNulls<String>(1)
                arr[0] = currentFile.absolutePath
                val uri = Uri.fromFile(newImageFile)
                broadcastNewFile(uri)

                if (newImageFile.exists() && newImageFile.isFile) ResultImage(
                    newImageFile.path,
                    newImageFile
                )
                else ResultImage(null, null)
            } else ResultImage(null, null)
        } catch (e: Exception) {
            e.printStackTrace()
            ResultImage(null, null)
        }
    }

    private fun getCompressedImageForQ(data: Intent, quality: Int): ResultImage {
        val imagePicker = ImagePicker.getFirstImageOrNull(data)
        return if (imagePicker == null) ResultImage(null, null)
        else {
            val path = imagePicker.path
            if (path.contains("file:/")) path.replace("file:/", "")

            val oldFileName = getOldFileName(path)
            val newFileName = getNewFileName()
            val currentFile = getImageDirectoryPathForQ(oldFileName)
            var compressedFile = currentFile

            try {
                compressedFile = compressImage(compressedFile.path, quality)
            } catch (e: IOException) {
                e.printStackTrace()
                ResultImage(null, null)
            }

            renameAndMoveImageFileForQ(currentFile, compressedFile, newFileName)
        }
    }

    @Throws(java.io.IOException::class)
    private fun compressImage(path: String, quality: Int): File {
        var file = File(path)
        if (!file.isFile) {
            try {
                file.createNewFile()
            } catch (e: java.io.IOException) {
                e.printStackTrace()
            }
        }

        if (file.exists()) {
            val compress = Compressor(context)
                .setQuality(quality)
                .setCompressFormat(CompressFormat.JPEG)
                .compressToFile(file)
            file = compress
        }

        return file
    }

    private fun renameImageFile(currentFile: File, newFile: File): ResultImage {
        if (currentFile.exists()) {
            if (currentFile.renameTo(newFile)) {
                val array = arrayOfNulls<String>(1)
                array[0] = currentFile.absolutePath
                deleteOldFile(array)
                val uri = Uri.fromFile(newFile)
                broadcastNewFile(uri)
                return ResultImage(newFile.path, newFile)
            }

            if (!newFile.exists() || !newFile.isFile) {
                return ResultImage(null, null)
            }
        }

        return ResultImage(null, null)
    }

    private fun broadcastNewFile(uri: Uri) {
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = uri
        context.sendBroadcast(intent)
    }

    private fun deleteOldFile(paths: Array<String?>) {
        context.contentResolver.delete(Media.EXTERNAL_CONTENT_URI, "_data=?", paths)
    }

    private fun getImageDirectoryPath(imageName: String): File =
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absoluteFile,
            "$applicationName/$imageName"
        )

    private fun getOldFileName(fileName: String): String {
        return fileName.substring(fileName.lastIndex)
    }

    private fun getNewFileName(): String {
        val builder = java.lang.StringBuilder()
        val locale = Locale.getDefault()
        val name = applicationName
        val upperCase = name.uppercase(locale)
        return builder.append(upperCase).append("_IMG_").append(System.currentTimeMillis())
            .append(".jpg").toString()
    }

    fun compressAndCropImage(activity: Activity, data: Intent?, quality: Int) {
        if (VERSION.SDK_INT >= 29) {
            val result = getCompressedImageForQ(data!!, quality)
            startCropping(activity, result)
        } else {
            val result = getCompressedImageBelowQ(data!!, quality)
            startCropping(activity, result)
        }
    }

    private fun startCropping(activity: Activity, resultImage: ResultImage) {
        val uri = Uri.fromFile(resultImage.file)
        UCrop.of(uri, uri).start(activity)
    }

    fun cropImage(activity: Activity, data: Intent?) {
        if (VERSION.SDK_INT >= 29) {
            val result = getOriginalImageForQ(data!!)
            startCropping(activity, result)
        } else {
            val result = getOriginalImageBelowQ(data!!)
            startCropping(activity, result)
        }
    }

    fun cropImageFromGallery(activity: Activity, data: Intent?) {
        val resultImage = getOriginalImageFromGallery(data)
        startCroppingFromGallery(activity, resultImage)
    }

    private fun startCroppingFromGallery(activity: Activity, resultImage: ResultImage){
        val uri = Uri.fromFile(resultImage.file)
        val destFileName = getNewFileName()
        val desFile = if (VERSION.SDK_INT >= 29) getImageDirectoryPathForQ(destFileName) else getImageDirectoryPath(destFileName)
        if (!desFile.exists()){
            desFile.createNewFile()
        }

        val destUri = Uri.fromFile(desFile)
        UCrop.of(uri, destUri).start(activity)
    }


    @Parcelize
    data class ResultImage(
        val path: String? = emptyString(),
        val file: File?,
    ) : Parcelable

    companion object {
        fun isFileSizeOk(filePath: String): Boolean {
            val file = File(filePath)
            val bytes: Double = file.length().toDouble()
            val kilobytes: Double = bytes / 1024
            val megabytes: Double = kilobytes / 1024
            return megabytes >= 3.5
        }
    }

}