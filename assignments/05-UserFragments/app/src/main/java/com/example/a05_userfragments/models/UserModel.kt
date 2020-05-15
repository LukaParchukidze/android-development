package com.example.a05_userfragments.models

import com.google.gson.annotations.SerializedName

class UserModel {
    var page = 0

    @SerializedName("per_page")
    var perPage = 0

    var total = 0

    @SerializedName("total_pages")
    var totalPages = 0

    var data = ArrayList<Data>()

    class Data() {
        var id = 0
        var email = ""

        @SerializedName("first_name")
        var firstName = ""

        @SerializedName("last_name")
        var lastName = ""

        var avatar = ""
    }

    class User {
        var data = Data()
    }
}