package com.example.wastewise.data.remote.response.login

import com.google.gson.annotations.SerializedName

data class Message(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("custom_data")
	val customData: CustomData
)

data class CustomData(
	val any: Any? = null
)
