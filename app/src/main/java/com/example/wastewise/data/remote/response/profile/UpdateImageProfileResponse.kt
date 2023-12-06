package com.example.wastewise.data.remote.response.profile

import com.google.gson.annotations.SerializedName

data class UpdateImageProfileResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
