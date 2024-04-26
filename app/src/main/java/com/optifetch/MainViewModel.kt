package com.optifetch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.optifetch.network.RemoteDataSource
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val dataSrc = PostRepository(RemoteDataSource(ApiClient.apiService))
    val posts: LiveData<PagingData<Post>> = dataSrc.getPosts().cachedIn(viewModelScope)

}