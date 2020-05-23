package com.example.midtermn1.music

interface CustomCallback {
    fun onSuccess(body: String) {}
    fun onFailed(error: String) {}
}