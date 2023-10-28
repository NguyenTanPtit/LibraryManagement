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


    var tokenExpiredDialogIsShowing = false
    var isWaitingNextTokenExpired = false

    suspend fun removeWaitingNextTokenExpired() {
        delay(5000) // delay 5s
        isWaitingNextTokenExpired = false
    }
    val chuckerCollector = ChuckerCollector(
        context = appContext,
        // Toggles visibility of the notification
        showNotification = true,
        // Allows to customize the retention period of collected data
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    // Create the Interceptor
    val chuckerInterceptor = ChuckerInterceptor.Builder(appContext)
        // The previously created Collector
        .collector(chuckerCollector)
        // The max body content length in bytes, after this responses will be truncated.
        .maxContentLength(250_000L)
        // List of headers to replace with ** in the Chucker UI
        .redactHeaders("Auth-Token", "Bearer")
        // Read the whole response body even when the client does not consume the response completely.
        // This is useful in case of parsing errors or when the response body
        // is closed before being read like in Retrofit with Void and Unit types.
        .alwaysReadResponseBody(true)
        // Use decoder when processing request and response bodies. When multiple decoders are installed they
        // Controls Android shortcut creation.
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