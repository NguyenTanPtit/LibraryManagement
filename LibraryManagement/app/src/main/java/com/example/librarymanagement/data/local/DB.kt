package com.example.librarymanagement.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.librarymanagement.data.local.user.UserDAO
import com.example.librarymanagement.models.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class DB : RoomDatabase(){

    abstract fun getUserDAO(): UserDAO

    companion object{

        @Volatile
        private var instance: DB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, DB::class.java,"database_library"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }
}