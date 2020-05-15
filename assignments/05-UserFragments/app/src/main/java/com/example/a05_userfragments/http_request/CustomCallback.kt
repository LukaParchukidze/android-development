package com.example.a05_userfragments.http_request

interface CustomCallback {
    fun onSuccess(body: String) {}
    fun onFailed(error: String) {}
}