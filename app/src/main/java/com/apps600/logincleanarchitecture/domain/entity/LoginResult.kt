package com.apps600.logincleanarchitecture.domain.entity

data class LoginResult(val isSuccessful : Boolean,
                       val successResult: LoginSuccessModel? = null,
                       val failureResult: LoginFailureModel? = null
                       )
