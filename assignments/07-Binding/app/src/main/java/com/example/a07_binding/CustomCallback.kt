package com.example.a07_binding

interface CustomCallback {
    fun onSuccess(body: String) {}
    fun onFailed(error: String) {}
}