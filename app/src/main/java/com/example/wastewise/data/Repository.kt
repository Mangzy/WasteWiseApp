package com.example.wastewise.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.wastewise.data.remote.network.ApiService
import com.example.wastewise.data.remote.network.ForgotPasswordRequest
import com.example.wastewise.data.remote.network.LoginRequest
import com.example.wastewise.data.remote.network.RefreshTokenRequest
import com.example.wastewise.data.remote.network.SignUpRequest
import com.example.wastewise.data.remote.network.UpdateProfileRequest
import com.example.wastewise.data.remote.response.login.ForgotPasswordResponse
import com.example.wastewise.data.remote.response.login.LoginResponse
import com.example.wastewise.data.remote.response.login.RefreshTokenResponse
import com.example.wastewise.data.remote.response.profile.ProfileResponse
import com.example.wastewise.data.remote.response.profile.UpdateImageProfileResponse
import com.example.wastewise.data.remote.response.profile.UpdateProfileResponse
import com.example.wastewise.data.remote.response.register.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.HttpException

class Repository(private val apiService: ApiService) {

    fun postSignUp(email: String, password: String): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val signUpRequest = SignUpRequest(email, password)
            val response = apiService.postSignUp(signUpRequest)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val code = e.code()
            Log.e("Repository", "postSignUp: $code")
            val errorMessage = when (code) {
                500 -> "The email address is already in use by another account."
                else -> "Error HTTP: $code"
            }
            emit(Result.Error(errorMessage))
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Error jaringan"
            emit(Result.Error(errorMessage))
        }
    }

    fun postLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val loginRequest = LoginRequest(email, password)
            val response = apiService.postLogin(loginRequest)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val code = e.code()
            Log.e("Repository", "postSignUp: $code")
            val errorMessage = when (code) {
                400 -> "There is no user record corresponding to this identifier. The user may have been deleted."
                401 -> "Email not verified"
                else -> "Error HTTP: $code"
            }
            emit(Result.Error(errorMessage))
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Error jaringan"
            emit(Result.Error(errorMessage))
        }
    }

    fun postForgotPassword(email: String): LiveData<Result<ForgotPasswordResponse>> = liveData {
        emit(Result.Loading)
        try {
            val forgotPasswordRequest = ForgotPasswordRequest(email)
            val response = apiService.postForgotPassword(forgotPasswordRequest)
            emit(Result.Success(response))
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Error jaringan"
            emit(Result.Error(errorMessage))
        }
    }

    fun postRefreshToken(refreshToken: String): LiveData<Result<RefreshTokenResponse>> = liveData {
        emit(Result.Loading)
        try {
            val refreshTokenRequest = RefreshTokenRequest(refreshToken)
            val response = apiService.postRefreshToken(refreshTokenRequest)
            emit(Result.Success(response))
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Error jaringan"
            emit(Result.Error(errorMessage))
        }
    }

    fun getProfile(): LiveData<Result<ProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getProfile()
            emit(Result.Success(response))
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Error jaringan"
            emit(Result.Error(errorMessage))
        }
    }

    fun updateProfile(displayName: String): LiveData<Result<UpdateProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val updateProfileRequest = UpdateProfileRequest(displayName)
            val response = apiService.updateProfile(updateProfileRequest)
            emit(Result.Success(response))
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Error jaringan"
            emit(Result.Error(errorMessage))
        }
    }

    fun updateProfilePicture(profilePicture: MultipartBody.Part): LiveData<Result<UpdateImageProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateProfilePicture(profilePicture)
            emit(Result.Success(response))
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Error jaringan"
            emit(Result.Error(errorMessage))
        }
    }
}