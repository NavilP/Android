package com.example.dowloader_app

interface Downloader {
    fun downloadFile(url: String): Long
}