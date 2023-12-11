package com.example.wastewise.ui.detail__article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wastewise.data.Repository
import com.example.wastewise.data.remote.response.article.Article

class DetailArticleViewModel(private val repository: Repository) : ViewModel() {
    fun getArticleDetail(articleId: String) = repository.getArticleDetails(articleId)

}