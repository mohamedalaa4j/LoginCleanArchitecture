package com.apps600.logincleanarchitecture.data.remote

import com.apps600.logincleanarchitecture.domain.entity.LoginSuccessModel
import com.apps600.logincleanarchitecture.domain.entity.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body user: User ):Response<LoginSuccessModel>
//    suspend fun login(@Body user: User ):Response<String>
}