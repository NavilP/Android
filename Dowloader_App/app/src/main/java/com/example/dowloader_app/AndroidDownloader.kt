package com.example.dowloader_app

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AndroidDownloader(private val context: MainActivity) : Downloader {
    private val downloadManager: DownloadManager
    override fun downloadFile(url: String): Long {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Si no se tienen permisos, solicitarlos al usuario
            ActivityCompat.requestPermissions(
                (context as Activity), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                WRITE_EXTERNAL_STORAGE_PERMISSION_CODE
            )
            // En este punto, la descarga debería ser iniciada por el usuario después de conceder permisos
            -1L
        } else {
            // Se tienen permisos, iniciar descarga
            val request = DownloadManager.Request(Uri.parse(url))
                .setMimeType("image/jpeg")
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle("image.jpg")
                .addRequestHeader("Authorization", "Bearer <token>")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
            downloadManager.enqueue(request)
        }
    }

    init {
        downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    companion object {
        private const val WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 1001
    }
}

