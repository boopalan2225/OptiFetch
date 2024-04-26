package com.optifetch.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class PostResult(
    val page: Int,
    val results: List<Post>,
    val total_pages: Int,
    val total_results: Int
)

@Entity(tableName = "UserDetails")
data class Post(
    val userId: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val body: String
)