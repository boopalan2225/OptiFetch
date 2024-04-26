package com.optifetch.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.optifetch.models.Post
import com.optifetch.network.IRemoteDataSource

class PostPagingSource(
    private val remoteDataSource: IRemoteDataSource
) :
    PagingSource<Int, Post>() {

        private val TAG = PostPagingSource::class.simpleName

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val position = params.key ?: 1
            val response = remoteDataSource.fetchPostList()

            if (response.isSuccess) {
                LoadResult.Page(
                    data = response.getOrThrow(),
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == 1 /*response.data.total_pages*/) null else (position + 1)
                )
            } else {
                Log.i(TAG, "load: No Response")
                LoadResult.Error(throw Exception("No Response"))
            }

        } catch (e: Exception) {
            Log.i(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}