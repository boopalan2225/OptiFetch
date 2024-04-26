package com.optifetch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.optifetch.models.Post
import com.optifetch.network.ApiClient
import com.optifetch.network.RemoteDataSource
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val remoteData = RemoteDataSource(ApiClient.apiService)
    private val dataSrc = PostRepository(remoteData)
    val posts: LiveData<PagingData<Post>> = dataSrc.getPosts().cachedIn(viewModelScope)
    private val dao = OptiFetchApplication.database.userDao()

    init {
        viewModelScope.launch {
            dao.clearTable()
            val response = remoteData.fetchPostList()
            response.getOrNull()?.let { dao.insert(it) }

        }
    }
}