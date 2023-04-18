package com.apps600.logincleanarchitecture.domain.useCase

import com.apps600.logincleanarchitecture.domain.entity.LoginErrorModel
import com.apps600.logincleanarchitecture.domain.entity.LoginSuccessModel
import com.apps600.logincleanarchitecture.domain.entity.User
import com.apps600.logincleanarchitecture.domain.repository.Repository
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: Repository) {

    suspend fun doLogin(user: User): String {

        return if (repository.login(user).isSuccessful) {
            repository.login(user).body()?.token!!
        } else {
            val json = JSONObject(repository.login(user).errorBody()!!.charStream().readText())
            val errorResponseObject = Gson().fromJson(json.toString(), LoginErrorModel::class.java)

            // jsonObj.toString()
            errorResponseObject.message
        }

    }
}