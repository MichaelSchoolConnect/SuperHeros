package com.lebogang.superheros.retrofit

import com.google.gson.annotations.SerializedName
class UsersList {
    @SerializedName("items")
    var users: List<Users>? = null
}