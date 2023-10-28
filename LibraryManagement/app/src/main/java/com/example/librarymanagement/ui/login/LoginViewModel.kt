package com.example.librarymanagement.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.remote.login.LoginResponse
import com.example.librarymanagement.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (private val repo: LoginRepository):ViewModel() {

    private var _login = MutableLiveData<Resource<LoginResponse?>>()
    val login : MutableLiveData<Resource<LoginResponse?>>
        get() = _login

   fun login(username:String,password:String){
       _login.value = Resource.Loading()
       viewModelScope.launch {
           repo.login(username,password).onResult(
               {
                    _login.value = Resource.Success(it)
               },{
                    _login.value = Resource.Error(it)
               }
           )
       }

    }
}