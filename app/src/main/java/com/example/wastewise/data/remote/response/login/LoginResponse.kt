package com.example.wastewise.data.remote.response.login

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("token")
    val token: String,

    @SerializedName("refreshToken")
    val refreshToken: String,

    @SerializedName("error")
    val error: Message,
)