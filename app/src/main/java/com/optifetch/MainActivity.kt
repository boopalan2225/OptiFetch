package com.optifetch

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.optifetch.adapters.LoaderAdapter
import com.optifetch.adapters.PostClickListener
import com.optifetch.adapters.PostPagingAdapter
import com.optifetch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            model = viewModel
        }
        setUpStatusBar()
        setContentView(binding.root)

        initializeAdapter()
    }

    private fun setUpStatusBar() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeAdapter() {
        val adapter = PostPagingAdapter(PostClickListener {

        })

        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.recyleView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
        }

        adapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.NotLoading -> {
                    if (loadState.source.refresh is LoadState.NotLoading) {
                        binding.recyleView.visibility = View.VISIBLE
                        if (loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                            Log.i(TAG, "initializeAdapter: empty list")
                        } else {
                            Log.i(TAG, "initializeAdapter: list present")
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyleView.visibility = View.GONE
                }
                is LoadState.Error -> {
                    Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.posts.observe(this) { posts ->
            posts?.let {
                adapter.submitData(this.lifecycle, posts)
            }
        }
    }
}
