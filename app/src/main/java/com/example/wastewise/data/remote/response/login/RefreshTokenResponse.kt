package com.example.wastewise.data.remote.response.login

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(

	@SerializedName("status")
	val status: String,

	@SerializedName("token")
	val token: String,

	@SerializedName("refreshToken")
	val refreshToken: String
)

