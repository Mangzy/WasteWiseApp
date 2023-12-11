package com.example.wastewise.data.remote.response.article

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("data")
	val data: List<Article>,

	@field:SerializedName("status")
	val status: String
)


