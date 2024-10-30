package com.example.infinitydogs.api

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("message")
    val imageLink: String
)