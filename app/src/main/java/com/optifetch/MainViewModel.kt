package com.optifetch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.optifetch.network.RemoteDataSource
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val posts = MutableLiveData<String>()
    private val dataSrc = RemoteDataSource(ApiClient.apiService)

    fun onClickFetchAllResponse() {
        viewModelScope.launch {
            posts.value = dataSrc.fetchPostList().toString()
        }
    }

    fun onClickCallApi() {
        viewModelScope.launch {
            posts.value = dataSrc.fetchPostDetail(postId = 1).toString()
        }
    }
}