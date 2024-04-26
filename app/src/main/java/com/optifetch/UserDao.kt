package com.optifetch

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import com.optifetch.models.Post

@Dao
interface UserDao {
    @Insert
    suspend fun insert(data: List<Post>)

    @Query("SELECT * FROM UserDetails")
    suspend fun getAll(): List<Post>

    @Query("DELETE FROM UserDetails")
    suspend fun clearTable()
}

@Database(entities = [Post::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}