package com.example.wastewise.ui.booklet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wastewise.data.Repository
import com.example.wastewise.data.remote.response.article.Article

class BookletViewModel(private val repository: Repository) : ViewModel() {
    fun getBooklet()  = repository.getWaste()
}