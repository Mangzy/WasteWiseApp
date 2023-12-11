package com.example.wastewise.data.remote.response.article

import com.google.gson.annotations.SerializedName

data class Article(

    @field:SerializedName("date")
    val date: Date,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("content_id")
    val contentId: String,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("title")
    val title: String
)
