package com.optifetch

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btn)
        val tv = findViewById<TextView>(R.id.textView)
        button.setOnClickListener {
            val postId = 1
            val call = ApiClient.apiService.getPostById(postId)

            call.enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        val post = response.body()
                        tv.text = post.toString()
                    } else {
                        // Handle error
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}