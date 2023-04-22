package com.apps600.logincleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps600.logincleanarchitecture.domain.entity.User
import com.apps600.logincleanarchitecture.domain.useCase.LoginUseCase
import com.apps600.logincleanarchitecture.utilites.Resource
import com.apps600.logincleanarchitecture.utilites.USER_TOKEN_DATASTORE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _showToast = Channel<Resource>()
    val showToast = _showToast.receiveAsFlow()

    private val _showProgressbar = Channel<Resource>()
    val showProgressbar = _showProgressbar.receiveAsFlow()

    fun login(user: User) {
        viewModelScope.launch {
            _showProgressbar.send(Resource.Loading())
            val loginResult = loginUseCase.doLogin(user)

            if (loginResult.isSuccessful) {
                loginResult.successResult?.email
                loginUseCase.saveToken(USER_TOKEN_DATASTORE_KEY, loginResult.successResult?.token!!)
                _showToast.send(Resource.Success("Logged in"))
            } else {
                loginResult.failureResult?.message
                _showToast.send(Resource.Error(loginResult.failureResult?.message!!))
            }
            _showProgressbar.send(Resource.Success())
        }

    }
}


