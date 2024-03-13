package com.conexa.conexachallenge.data.api.model.response

data class News(
    val id: Int,
    val title: String,
    val content: String,
    val image: String,
    val thumbnail: String,
    val category: String,
    val publishedAt: String,
    val updatedAt: String,
    val userId: Int
)