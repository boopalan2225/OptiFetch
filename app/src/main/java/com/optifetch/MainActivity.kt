package com.optifetch

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.optifetch.network.RemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        button1Actions()
        button2Actions()
    }

    private fun button2Actions() {
        val button = findViewById<Button>(R.id.btn2)
        val tv = findViewById<TextView>(R.id.textView)
        button.setOnClickListener {

        }
    }

    private fun button1Actions() {
        val button = findViewById<Button>(R.id.btn)
        val tv = findViewById<TextView>(R.id.textView)
        button.setOnClickListener {

        }
    }
}