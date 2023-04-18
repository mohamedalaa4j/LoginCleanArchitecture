package com.apps600.logincleanarchitecture.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.apps600.logincleanarchitecture.domain.entity.User
import com.apps600.logincleanarchitecture.domain.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    suspend fun login(user: User) {
        Log.e("result", loginUseCase.doLogin(user))

    }


}