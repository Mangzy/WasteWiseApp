package com.example.wastewise.ui.detail__article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wastewise.R
import com.example.wastewise.data.Result
import com.example.wastewise.data.remote.response.detail_article.ContentItem
import com.example.wastewise.databinding.ActivityDetailArticleBinding
import com.example.wastewise.utils.ViewModelFactory

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding
    private val detailArticleViewModel by viewModels<DetailArticleViewModel> {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID).toString()
        val title = intent.getStringExtra(EXTRA_TITLE).toString()


        detailArticleViewModel.getArticleDetail(id).observe(this) {
            if (it != null) {
                Log.d("DetailArticleActivity", "onCreate: $it.data.data[0].content")
//                check result success or error
                Log.d("DetailArticleActivity", "onCreate: ${Result.Success(it)}")
                when (it) {
                    is Result.Success -> {
                        binding.titleArticle.text = title
                        Log.d("DetailArticleActivity", "onCreate: check}")
                        showLoading(false)
                        val contentList = it.data.data[0].content
                        Log.d("DetailArticleActivity", "onCreate: $contentList")
                        getData(contentList)
                        binding.recyclerViewArticle.layoutManager = LinearLayoutManager(this)
                        binding.recyclerViewArticle.setHasFixedSize(true)
                    }

                    is Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Error -> {
                        showLoading(false)
                    }
                }
            } else {
            }
        }
    }

    private fun getData(contentList: List<ContentItem>) {
        val adapter = DetailAdapter()

        adapter.submitList(contentList)


        binding.recyclerViewArticle.adapter = adapter

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicatorDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
    }
}