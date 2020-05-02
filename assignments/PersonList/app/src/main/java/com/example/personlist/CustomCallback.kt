package com.example.personlist

interface CustomCallback {
    fun onSuccess(result: String) {}
    fun onFailed(errorMessage: String) {}
}
