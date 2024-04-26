package com.optifetch.models

import com.optifetch.Post

data class PostResult(
    val page: Int,
    val results: List<Post>,
    val total_pages: Int,
    val total_results: Int
)