package com.example.librarymanagement.base

import retrofit2.Response
import java.io.EOFException

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> T): Resource<T> {
        return try {
            val response = call()
            Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }

    protected suspend fun <T> getResultWithResponse(call: suspend () -> Response<T>): Resource<T?> {
        var responseCode = 0
        return try {
            val response = call()
            responseCode = response.code()
            Resource.Success(if (responseCode == 204) {
                null
            } else {
                response.body()
            })
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is EOFException || responseCode == 200) {
                Resource.Success(null)
            } else Resource.Error(e)
        }
    }

//    protected suspend fun <T> getByResult(call: suspend () -> T): Result<T> {
//        return try {
//            val response = call()
//            Result.Success(response)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Result.Error(e)
//        }
//    }

}