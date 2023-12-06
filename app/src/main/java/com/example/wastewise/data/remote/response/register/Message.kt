package com.example.wastewise.data.remote.response.register

import com.google.gson.annotations.SerializedName

data class Message(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("message")
	val message: String,

	@SerializedName("status")
	val status: String,

	)


