package com.optifetch

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.optifetch.network.IRemoteDataSource
import com.optifetch.paging.PostPagingSource


class PostRepository (
    private val remoteDataSource: IRemoteDataSource
) {

    fun getPosts(): LiveData<PagingData<Post>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 200),
        pagingSourceFactory = { PostPagingSource(remoteDataSource) }
    ).liveData

    /*suspend fun getPostDetail(id: Int): Result<Post> =
        remoteDataSource.fetchPostDetail(id)*/

}