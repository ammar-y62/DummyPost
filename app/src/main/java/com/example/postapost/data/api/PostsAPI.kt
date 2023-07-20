package com.example.postapost.data.api

import com.example.postapost.data.models.GetPostResponse
import com.example.postapost.data.models.Post
import com.example.postapost.globalUse.ALL_POSTS
import com.example.postapost.globalUse.POST_A_POST
import com.example.postapost.globalUse.SEARCH_POST
import retrofit2.Response
import retrofit2.http.*

interface PostsAPI {

    @GET(ALL_POSTS)
    suspend fun getAllPosts(
    ): Response<GetPostResponse>


    @GET
    suspend fun searchForPosts(
        @Url endpoint: String,
    ): Response<GetPostResponse>

    @Headers("Content-Type: application/json")
    @POST(POST_A_POST)
    suspend fun shareAPost(@Body post: Post): Response<Post>

}