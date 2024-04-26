package com.optifetch.network

import com.optifetch.models.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int): Response<Post>

    @GET("posts")
    suspend fun getPost(): Response<List<Post>>
}