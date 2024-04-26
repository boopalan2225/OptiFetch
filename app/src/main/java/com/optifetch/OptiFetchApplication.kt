package com.optifetch

import android.app.Application
import androidx.room.Room

class OptiFetchApplication : Application() {
    companion object {
        lateinit var database: UserDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "UserDetails"
        ).build()
    }
}