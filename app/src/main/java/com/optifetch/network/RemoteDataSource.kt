package com.optifetch.network

import com.optifetch.Post
import retrofit2.Response

class RemoteDataSource /*@Inject constructor*/(private val apiService: ApiService) :
    IRemoteDataSource {
     override suspend fun fetchPostList(
         page: Int
     ): Result<List<Post>> =

         getResult {
        apiService.getPost()
    }

     override suspend fun fetchPostDetail(postId: Int) = getResult {
        apiService.getPostById(postId)
    }

    private suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

}

interface IRemoteDataSource {
    suspend fun fetchPostList(
        page: Int = 1
    ): Result<List<Post>>

    suspend fun fetchPostDetail(postId: Int): Result<Post>
}