package com.example.wastewise.data.remote.response.booklet

data class BookletResponse(
	val data: List<Booklet>,
	val status: String
)

data class Booklet(
	val image: String,
	val type: String,
	val name: String,
	val description: String,
	val processingId: List<String>,
	val id: String
)

