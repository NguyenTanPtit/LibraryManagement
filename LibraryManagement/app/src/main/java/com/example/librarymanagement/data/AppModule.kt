package com.example.librarymanagement.data

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import androidx.annotation.RequiresApi
import com.example.librarymanagement.BuildConfig
import com.example.librarymanagement.data.remote.categories.CategoriesService
import com.example.librarymanagement.data.remote.common.ApiHttpClient
import com.example.librarymanagement.data.remote.login.LoginService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiHttpClient(
        @ApplicationContext appContext: Context,
    ) = ApiHttpClient(appContext)

    @Singleton
    @Provides
    @Named("provideApiHttpClient60")
    fun provideApiHttpClient60(
        @ApplicationContext appContext: Context
    ) = ApiHttpClient(appContext, 60L)

    @Singleton
    @Provides
    @Named("provideApiHttpClient120")
    fun provideApiHttpClient120(
        @ApplicationContext appContext: Context
        ) = ApiHttpClient(appContext, 120L)


    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        httpClient: ApiHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build()

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    @Named("provideRetrofit60")
    fun provideRetrofit60(
        gson: Gson,
        @Named("provideApiHttpClient60") httpClient: ApiHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build()

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    @Named("provideRetrofit120")
    fun provideRetrofit120(
        gson: Gson,
        @Named("provideApiHttpClient120") httpClient: ApiHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    fun provideCategoriesService(retrofit: Retrofit): CategoriesService =
        retrofit.create(CategoriesService::class.java)
}