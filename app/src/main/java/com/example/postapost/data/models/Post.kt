package com.example.postapost.data.models

import androidx.annotation.Keep

@Keep
data class Post(
    val body: String,
    val id: Int,
    val reactions: Int,
    val tags: List<String>,
    val title: String,
    val userId: Int
)