package com.example.postapost.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapost.data.api.RequestState
import com.example.postapost.data.models.GetPostResponse
import com.example.postapost.data.models.Post
import com.example.postapost.data.repositories.PostsRepository
import com.example.postapost.globalUse.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: PostsRepository,
) : ViewModel() {

    private val _posts: MutableLiveData<RequestState<GetPostResponse>> = MutableLiveData()
    val posts: LiveData<RequestState<GetPostResponse>> = _posts
    var postsResponse: GetPostResponse? = null

    var postUpdated: MutableLiveData<Response<Post>?> = MutableLiveData(null)

    init {
        if (isInternetAvailable(repository.context))
            getAllPosts()
    }

    fun getAllPosts() = viewModelScope.launch {
        val response = repository.getAllPosts()
        _posts.postValue(handlePostsResponse(response))
    }


    fun searchForPosts(query: String) = viewModelScope.launch {
        val response = repository.searchForPost(query)
        _posts.postValue(handlePostsResponse(response))
    }

    fun sharePost(title: String, body: String, tagsList: List<String>) =
        viewModelScope.launch {
            val post = Post(body, 31, 15, tagsList, title, 45)
            val response = repository.postAPost(post)
            postUpdated.value = if (response.isSuccessful) response else null
        }

    private fun handlePostsResponse(response: Response<GetPostResponse>): RequestState<GetPostResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                if (postsResponse == null) {
                    postsResponse = result
                } else {
//                    val oldArticles = postsResponse?.posts
//                    val newArticles = result.posts
//                    oldArticles?.addAll(newArticles)
                    postsResponse = result
                }
                return RequestState.Sucess(postsResponse ?: result)
            }
        }
        return RequestState.Error(response.message())
    }

}