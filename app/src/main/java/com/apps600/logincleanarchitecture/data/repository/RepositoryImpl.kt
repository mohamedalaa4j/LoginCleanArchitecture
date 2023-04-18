package com.apps600.logincleanarchitecture.data.repository

import com.apps600.logincleanarchitecture.data.remote.ApiService
import com.apps600.logincleanarchitecture.domain.entity.LoginSuccessModel
import com.apps600.logincleanarchitecture.domain.entity.User
import com.apps600.logincleanarchitecture.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService, private val ioDispatcher: CoroutineDispatcher
) : Repository {


//    override suspend fun login(user: User): Response<String> =
    override suspend fun login(user: User): Response<LoginSuccessModel> =
        withContext(ioDispatcher) { apiService.login(user)

        }
}