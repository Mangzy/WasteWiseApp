package com.example.wastewise.data.remote.response.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("photoURL")
	val photoURL: String,

	@field:SerializedName("displayName")
	val displayName: String,

	@field:SerializedName("email")
	val email: String
)
