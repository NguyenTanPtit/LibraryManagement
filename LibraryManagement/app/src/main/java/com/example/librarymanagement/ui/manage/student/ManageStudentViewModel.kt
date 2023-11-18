package com.example.librarymanagement.ui.manage.student

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagement.base.Resource
import com.example.librarymanagement.base.onResult
import com.example.librarymanagement.data.repository.UserRepository
import com.example.librarymanagement.models.ApiResponse
import com.example.librarymanagement.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageStudentViewModel @Inject constructor(val repo: UserRepository) : ViewModel(){

    private var _getAllStudent = MutableLiveData<Resource<List<User>?>>()

    val getAllStudent: MutableLiveData<Resource<List<User>?>>
        get() = _getAllStudent

    private var _deleteStudent = MutableLiveData<Resource<ApiResponse<User>?>>()

    val deleteStudent: MutableLiveData<Resource<ApiResponse<User>?>>
        get() = _deleteStudent

    private var _createStudent = MutableLiveData<Resource<ApiResponse<User>?>>()

    val createStudent: MutableLiveData<Resource<ApiResponse<User>?>>
        get() = _createStudent

    private var _updateStudent = MutableLiveData<Resource<ApiResponse<User>?>>()

    val updateStudent: MutableLiveData<Resource<ApiResponse<User>?>>
        get() = _updateStudent
    fun getAllStudent(){
        _getAllStudent.value = Resource.Loading()
        viewModelScope.launch {
            repo.getAll().onResult(
                onSuccess = {
                    _getAllStudent.value = Resource.Success(it)
                },
                onError = {
                    _getAllStudent.value = Resource.Error(it)
                }
            )
        }
    }

    fun deleteStudent(id: Long){
        _deleteStudent.value = Resource.Loading()
        viewModelScope.launch {
            repo.delete(id.toInt()).onResult(
                onSuccess = {
                    _deleteStudent.value = Resource.Success(it)
                },
                onError = {
                    _deleteStudent.value = Resource.Error(it)
                }
            )
        }
    }

    fun createStudent(user: User){
        _createStudent.value = Resource.Loading()
        viewModelScope.launch {
            repo.create(user).onResult(
                onSuccess = {
                    _createStudent.value = Resource.Success(it)
                },
                onError = {
                    _createStudent.value = Resource.Error(it)
                }
            )
        }
    }

    fun updateStudent(user: User){
        _updateStudent.value = Resource.Loading()
        viewModelScope.launch {
            repo.update(user).onResult(
                onSuccess = {
                    _updateStudent.value = Resource.Success(it)
                },
                onError = {
                    _updateStudent.value = Resource.Error(it)
                }
            )
        }
    }


}