package com.example.wastewise.data.remote.response.login

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(

    @field:SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: String,

    )