package com.apps600.logincleanarchitecture.utilites

//sealed class Holder<T>(val data: T? = null) {
//    data class Success<T>(val data: T? = null) : Holder(data)
//    data class Error<T>(val data: T) : Holder()
//
//}

sealed class Holder {

    data class Success<T>(val data: T) : Holder()
    data class Error<T>(val data: T) : Holder()

}

class Holder2<T>(val data: T)

open class MyState() {}
class MySuccess<T>(val data: T) : MyState()

class MyError<T>(val data: T) : MyState()

class MyHolder<out T : MyState>(val state: T) {
    fun get(): T {
        return state
    }
}

sealed class Resource {
    class Success(val message: String? = null) : Resource()
    class Error(val message: String? = null) : Resource()
    class Loading(val message: String? = null) : Resource()
}


//sealed class Holder (open val loginSuccessModel: LoginSuccessModel){
//    data class Success(override val loginSuccessModel: LoginSuccessModel) : Holder(loginSuccessModel)
//    //data class Error(val loginErrorModel: LoginErrorModel) : Holder()
//}