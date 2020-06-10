package com.example.a07_binding

import com.google.gson.annotations.SerializedName

class NewsModel() {
    var id: String? = ""
    var descriptionEN: String = ""
    var descriptionKA: String = ""
    var descriptionRU: String = ""
    var titleEN: String = ""
    var titleKA: String = ""
    var titleRU: String = ""
    var published: Int = 0
    @SerializedName("publish_date")
    var publishDate: String = ""
    @SerializedName("created_at")
    var createdAt: String = ""
    @SerializedName("updated_at")
    var updatedAt: String = ""
    var category: String = ""
    var cover: String = ""
    var isLast: Boolean = false
}