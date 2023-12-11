package com.example.wastewise.data.remote.response.article

import com.google.gson.annotations.SerializedName

data class Date(

    @field:SerializedName("_nanoseconds")
    val nanoseconds: Int,

    @field:SerializedName("_seconds")
    val seconds: Int
)