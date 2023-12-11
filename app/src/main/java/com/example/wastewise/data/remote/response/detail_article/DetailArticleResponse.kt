//package com.example.wastewise.data.remote.response.detail_article
//
//import com.google.gson.annotations.SerializedName
//
//data class DetailArticleResponse(
//
//	@field:SerializedName("data")
//	val data: List<ArticleContent>,
//
//	@field:SerializedName("status")
//	val status: String
//)
//
//data class ArticleContent(
//
//	@field:SerializedName("url")
//	val url: String,
//
//	@field:SerializedName("content")
//	val content: List<ContentItem>
//)
//
//sealed class ContentItem {
//	data class TextContent(
//		val text: String
//	) : ContentItem()
//
//	data class ImageContent(
//		@SerializedName("image")
//		val image: String,
//		@SerializedName("caption")
//		val caption: String
//	) : ContentItem()
//}