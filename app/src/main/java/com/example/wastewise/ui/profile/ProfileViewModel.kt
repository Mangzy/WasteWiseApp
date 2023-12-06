package com.example.wastewise.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.wastewise.data.Repository
import com.example.wastewise.data.remote.response.profile.ProfileResponse
import okhttp3.MultipartBody

class ProfileViewModel(private val repository: Repository) : ViewModel() {
    fun getProfile()  = repository.getProfile()
    fun updateProfile(displayName: String) = repository.updateProfile(displayName)
    fun updateProfilePicture(photoURL: MultipartBody.Part) = repository.updateProfilePicture(photoURL)
}