package com.apps600.logincleanarchitecture.domain.useCase

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.apps600.logincleanarchitecture.domain.entity.LoginFailureModel
import com.apps600.logincleanarchitecture.domain.entity.LoginResult
import com.apps600.logincleanarchitecture.domain.entity.User
import com.apps600.logincleanarchitecture.domain.repository.Repository
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginUseCase @Inject constructor(
    private val repository: Repository,
    @ApplicationContext val context: Context
) {

    suspend fun doLogin(user: User): LoginResult {

        return if (repository.login(user).isSuccessful) {
            val loginSuccessResponse = repository.login(user).body()
            LoginResult(true, successResult = loginSuccessResponse)
        } else {
            val json = JSONObject(repository.login(user).errorBody()!!.charStream().readText())
            val loginFailureResponse = Gson().fromJson(json.toString(), LoginFailureModel::class.java)

            LoginResult(false, failureResult = loginFailureResponse)
        }
    }

    suspend fun saveToken(key: String, value: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun clearToken(key: String) {
        context.dataStore.edit {
            if (it.contains(stringPreferencesKey(key)))
                it.remove(stringPreferencesKey(key))
        }
    }

    suspend fun readToken(key: String) =
        context.dataStore.data.map {
            it[stringPreferencesKey(key)] ?: ""
        }

}