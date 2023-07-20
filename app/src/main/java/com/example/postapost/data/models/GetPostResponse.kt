package com.example.postapost.data.models

import androidx.annotation.Keep

@Keep
data class GetPostResponse(
    val limit: Int,
    val posts: MutableList<Post>,
    val skip: Int,
    val total: Int
)