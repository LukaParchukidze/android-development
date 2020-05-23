package com.example.midtermn1.math

import android.util.Log.d
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*


object MathHttpRequest {

    private const val HTTP_200_OK = 200
    private const val HTTP_201_CREATED = 201
    private const val HTTP_204_NO_CONTENT = 204
    private const val HTTP_400_BAD_REQUEST = 400
    private const val HTTP_401_UNAUTHORIZED = 401
    private const val HTTP_404_NOT_FOUND = 404
    private const val HTTP_500_INTERNAL_SERVER_ERROR = 500
    private const val HTTP_503_SERVICE_UNAVAILABLE = 503

    private var retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("http://numbersapi.com/")
        .build()


    var service = retrofit.create(ApiService::class.java)

    fun getRequest(number: String, type: String, callback: CustomCallback) {
        val call = service.getRequest(number, type)
        call.enqueue(onCallBack(callback))
    }

    fun getRequest(month: String, day: String, type: String, callback: CustomCallback) {
        val call = service.getRequest(month, day, type)
        call.enqueue(onCallBack(callback))
    }

    private fun onCallBack(callback: CustomCallback) = object : Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            d("onFailure", "${t.message}")
            callback.onFailed(t.message.toString())
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            d("response", "${response.body()}")

            val statusCode = response.code()

            if (statusCode == HTTP_200_OK || statusCode == HTTP_201_CREATED) {
                callback.onSuccess(response.body().toString())
            } else if (statusCode == HTTP_204_NO_CONTENT) {
                callback.onFailed("No Content Received")
            } else if (statusCode == HTTP_400_BAD_REQUEST || statusCode == HTTP_401_UNAUTHORIZED || statusCode == HTTP_404_NOT_FOUND || statusCode == HTTP_500_INTERNAL_SERVER_ERROR || statusCode == HTTP_503_SERVICE_UNAVAILABLE) {
                try {
                    val json = JSONObject(response.errorBody()!!.string())
                    if (json.has("error")) {
                        callback.onFailed(json.getString("error"))
                    } else if (json.has("message")) {
                        callback.onFailed(json.getString("message"))
                    }
                } catch (e: JSONException) {
                    d("exception", e.toString())
                }
            }
        }
    }


    interface ApiService {

        @GET("{number}/{type}")
        fun getRequest(@Path("number") number: String,
                       @Path("type") type: String): Call<String>

        @GET("{month}/{day}/{type}")
        fun getRequest(@Path("month") month: String,
                       @Path("day") day: String,
                        @Path("type") type: String): Call<String>
    }
}