package com.example.wastewise.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wastewise.data.Repository
import com.example.wastewise.data.remote.response.article.Article

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val article: LiveData<PagingData<Article>>  = repository.getArticle().cachedIn(viewModelScope)
}