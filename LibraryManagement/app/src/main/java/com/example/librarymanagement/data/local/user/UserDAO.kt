package com.example.librarymanagement.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.librarymanagement.models.User

@Dao
interface UserDAO {

    @Query("select * from User")
    suspend fun getUser(): User

}