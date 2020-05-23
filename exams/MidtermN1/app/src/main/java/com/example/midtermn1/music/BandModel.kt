package com.example.midtermn1.music

import com.google.gson.annotations.SerializedName

class BandModel() {
    var name: String? = ""

    @SerializedName("img_url")
    var imgUrl: String? = ""
    var thumb1: String? = ""
    var thumb2: String? = ""
    var thumb3: String? = ""
    var info: String? = ""
    var genre: String? = ""
}