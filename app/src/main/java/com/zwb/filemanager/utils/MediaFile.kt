package com.zwb.filemanager.utils

import android.content.Context
import android.provider.MediaStore

fun getAllMusicFiles(context: Context): List<String> {
    val musicFiles = mutableListOf<String>()

    val projection = arrayOf(MediaStore.Audio.Media.DATA)
    val selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0"
    val cursor = context.contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        selection,
        null,
        null
    )

    cursor?.use {
        val columnIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
        while (it.moveToNext()) {
            val filePath = it.getString(columnIndex)
            musicFiles.add(filePath)
        }
    }

    return musicFiles
}

fun getAllImages(context: Context): List<String> {
    val images = mutableListOf<String>()

    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        null
    )
    cursor?.use {
        val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        while (it.moveToNext()) {
            val imagePath = it.getString(columnIndex)
            images.add(imagePath)
        }
    }
    return images
}

fun getAllVideos(context: Context): List<String> {
    val videos = mutableListOf<String>()

    val projection = arrayOf(MediaStore.Video.Media.DATA)
    val cursor = context.contentResolver.query(
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        null
    )
    cursor?.use {
        val columnIndex = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
        while (it.moveToNext()) {
            val videoPath = it.getString(columnIndex)
            videos.add(videoPath)
        }
    }
    return videos
}

/**
 * 在你的Activity或Fragment中调用该函数
 *
 *  val extensions = arrayOf(".txt", ".doc")
 *  val files = getFilesWithExtensions(context, extensions)
 *
 */
fun getFilesWithExtensions(context: Context, vararg extensions: String): List<String> {
    val files = mutableListOf<String>()
    val projection = arrayOf(MediaStore.Files.FileColumns.DATA)
    val selection = StringBuilder()
    val selectionArgs = mutableListOf<String>()

    for (extension in extensions) {
        if (selection.isNotEmpty()) {
            selection.append(" OR ")
        }
        selection.append(MediaStore.Files.FileColumns.DATA)
        selection.append(" LIKE ?")
        selectionArgs.add("%$extension")
    }

    val cursor = context.contentResolver.query(
        MediaStore.Files.getContentUri("external"),
        projection,
        "(${MediaStore.Files.FileColumns.MEDIA_TYPE}=${MediaStore.Files.FileColumns.MEDIA_TYPE_NONE}) AND ($selection)",
        selectionArgs.toTypedArray(),
        null
    )

    cursor?.use {
        val columnIndex = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
        while (it.moveToNext()) {
            val filePath = it.getString(columnIndex)
            files.add(filePath)
        }
    }

    return files
}

fun getAllFileDetails(context: Context): List<String> {
    val fileDetails = mutableListOf<String>()

    val projection = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        MediaStore.Files.FileColumns.DATE_ADDED,
        MediaStore.Files.FileColumns.DATE_MODIFIED,
        MediaStore.Files.FileColumns.SIZE,
        MediaStore.Files.FileColumns.RELATIVE_PATH
    )

    val selection =
        "${MediaStore.Files.FileColumns.MEDIA_TYPE} != ${MediaStore.Files.FileColumns.MEDIA_TYPE_NONE}"
    val sortOrder = "${MediaStore.Files.FileColumns.DATE_MODIFIED} DESC"

    val cursor = context.contentResolver.query(
        MediaStore.Files.getContentUri("external"),
        projection,
        null,
        null,
        sortOrder
    )

    cursor?.use {
        val idColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
        val nameColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
        val addedColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)
        val modifiedColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)
        val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
        val pathColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.RELATIVE_PATH)

        while (it.moveToNext()) {
            val id = it.getLong(idColumn)
            val name = it.getString(nameColumn)
            val added = it.getLong(addedColumn)
            val modified = it.getLong(modifiedColumn)
            val size = it.getLong(sizeColumn)
            val path = it.getString(pathColumn)

            val fileDetail =
                "ID: $id, Name: $name, Added: $added, Modified: $modified, Size: $size, Path: $path"
            fileDetails.add(fileDetail)
        }
    }

    return fileDetails
}