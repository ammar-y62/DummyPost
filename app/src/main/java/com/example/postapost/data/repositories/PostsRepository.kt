package com.example.postapost.data.repositories

import android.content.Context
import com.example.postapost.data.api.RetrofitInstance
import com.example.postapost.data.models.GetPostResponse
import com.example.postapost.data.models.Post
import com.example.postapost.globalUse.SEARCH_POST
import retrofit2.Response

class PostsRepository(val context: Context) {


    suspend fun getAllPosts(): Response<GetPostResponse> {
        return RetrofitInstance.api.getAllPosts()
    }

    suspend fun searchForPost(query: String) =
        RetrofitInstance.api.searchForPosts(SEARCH_POST + query)

    suspend fun postAPost(post: Post) = RetrofitInstance.api.shareAPost(post)


}