package com.example.librarymanagement.data.remote.common

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import java.time.Duration
import java.util.*

class ApiHttpClient(
    val appContext : Context,
    private val timeOut: Long = 40L
) {
    val chuckerCollector = ChuckerCollector(
        context = appContext,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    // Create the Interceptor
    val chuckerInterceptor = ChuckerInterceptor.Builder(appContext)
        .collector(chuckerCollector)
        .maxContentLength(250_000L)
        .redactHeaders("Auth-Token", "Bearer")
        .alwaysReadResponseBody(true)
        .createShortcut(true)
        .build()

    @RequiresApi(Build.VERSION_CODES.O)
    fun build(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        val timeout = Duration.ofSeconds(timeOut)
        httpClientBuilder
            .readTimeout(timeout)
            .writeTimeout(timeout)
            .connectTimeout(Duration.ofSeconds(10))
            .retryOnConnectionFailure(true)

        httpClientBuilder.addInterceptor(chuckerInterceptor)
        return httpClientBuilder.build()
    }
}