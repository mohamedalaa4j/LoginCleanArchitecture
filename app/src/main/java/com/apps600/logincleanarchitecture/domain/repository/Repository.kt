package com.apps600.logincleanarchitecture.domain.repository

import com.apps600.logincleanarchitecture.domain.entity.LoginSuccessModel
import com.apps600.logincleanarchitecture.domain.entity.User
import okhttp3.ResponseBody
import retrofit2.Response

interface Repository {

   suspend fun login(user: User): Response<LoginSuccessModel>
//   suspend fun login(user: User): Response<String>
}