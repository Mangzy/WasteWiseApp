package com.example.wastewise.data.remote.network

import com.example.wastewise.data.remote.response.login.ForgotPasswordResponse
import com.example.wastewise.data.remote.response.login.LoginResponse
import com.example.wastewise.data.remote.response.login.RefreshTokenResponse
import com.example.wastewise.data.remote.response.profile.ProfileResponse
import com.example.wastewise.data.remote.response.profile.UpdateImageProfileResponse
import com.example.wastewise.data.remote.response.profile.UpdateProfileResponse
import com.example.wastewise.data.remote.response.register.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


data class SignUpRequest(
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class ForgotPasswordRequest(
    val email: String
)

data class RefreshTokenRequest(
    val refreshToken: String
)

data class UpdateProfileRequest(
    val displayName: String
)


interface ApiService {
    @POST("auth/register")
    suspend fun postSignUp(@Body signUpRequest: SignUpRequest): RegisterResponse

    @POST("auth/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): LoginResponse

    @POST("auth/forgotPassword")
    suspend fun postForgotPassword(
        @Body forgotPasswordRequest: ForgotPasswordRequest
    ): ForgotPasswordResponse

    @POST("auth/refreshToken")
    suspend fun postRefreshToken(
        @Body refreshTokenRequest: RefreshTokenRequest
    ): RefreshTokenResponse

    @GET("profile")
    suspend fun getProfile(): ProfileResponse

    @POST("profile/update")
    suspend fun updateProfile(
        @Body updateProfileRequest: UpdateProfileRequest
    ): UpdateProfileResponse

    @Multipart
    @POST("profile/uploadProfilePicture")
    suspend fun updateProfilePicture(
        @Part profilePicture: MultipartBody.Part
    ): UpdateImageProfileResponse

}