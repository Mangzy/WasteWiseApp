package com.example.wastewise.data.remote.response.detail_article

import com.google.gson.annotations.SerializedName

data class DetailArticleResponsee(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: String
)

sealed class ContentItem {
	data class TextContent(

		val text: String
	) : ContentItem()

	data class ImageContent(
		@SerializedName("image")
		val image: String,
		@SerializedName("caption")
		val caption: String
	) : ContentItem()
}

data class DataItem(

	@field:SerializedName("content")
	val content: List<ContentItem>,

	@field:SerializedName("url")
	val url: String
)
