package com.example.wastewise.data.remote.response.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@SerializedName("message")
	val message: String,

	@SerializedName("status")
	val status: String,

	@SerializedName("error")
	val error: Message,
)
